package com.example.a2024_want_to_shoot

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActionAdapter(private var items: List<ActionItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val originalItems = items.toMutableList()  // 원본 데이터를 보관합니다.
    private val TYPE_HEADER = 0
    private val TYPE_ACTION = 1

    private var lastClickTime: Long = 0
    private val doubleClickTime: Long = 300 // 300ms 간격을 두고 두 번 클릭하면 더블 클릭으로 인식

    // 검색 필터 메서드
    fun filter(query: String) {
        items = if (query.isEmpty()) {
            originalItems  // 검색어가 비어 있으면 원본 데이터로 되돌립니다.
        } else {
            originalItems.filter {
                when (it) {
                    is ActionItem.Action -> it.name.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true)
                    is ActionItem.Header -> it.title.contains(query, ignoreCase = true)
                }
            }
        }
        notifyDataSetChanged()  // RecyclerView 갱신
    }

    fun filter(query: String, category: String) {
        items = originalItems.filter {
            val matchesQuery = query.isEmpty() || (it is ActionItem.Action &&
                    (it.name.contains(query, true) || it.category.contains(query, true)))
            val matchesCategory = category == "전체" || (it is ActionItem.Action && it.category == category)
            matchesQuery && matchesCategory
        }
        notifyDataSetChanged()
    }

    // 카테고리 필터 메서드
    fun filterByCategory(category: String) {
        items = if (category == "전체") {
            originalItems  // "전체" 카테고리를 선택하면 모든 항목을 표시
        } else {
            originalItems.filter {
                when (it) {
                    is ActionItem.Action -> it.category == category
                    else -> false
                }
            }
        }
        notifyDataSetChanged()  // RecyclerView 갱신
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_ACTION -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_action, parent, false)
                ActionViewHolder(view, this)  // 어댑터 인스턴스를 전달
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is ActionViewHolder -> holder.bind(item as ActionItem.Action)
            is HeaderViewHolder -> holder.bind(item as ActionItem.Header)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ActionItem.Header -> TYPE_HEADER
            is ActionItem.Action -> TYPE_ACTION
        }
    }

    override fun getItemCount(): Int = items.size

    // ViewHolder 클래스에서 Adapter를 참조할 수 있도록 수정
    class ActionViewHolder(itemView: View, private val adapter: ActionAdapter) : RecyclerView.ViewHolder(itemView) {
        private val actionName: TextView = itemView.findViewById(R.id.action_name)
        private val actionCategory: TextView = itemView.findViewById(R.id.action_category)
        private val actionBookmark: ImageView = itemView.findViewById(R.id.action_bookmark)

        private var lastClickTime: Long = 0
        private val doubleClickTime: Long = 300 // 300ms 간격

        fun bind(actionItem: ActionItem.Action) {
            actionName.text = actionItem.name
            actionCategory.text = actionItem.category
            // 북마크 상태에 맞게 아이콘을 변경
            actionBookmark.setImageResource(if (actionItem.isBookmark) R.drawable.ic_fillstar else R.drawable.ic_nullstar)

            // 북마크 아이콘 클릭 리스너
            actionBookmark.setOnClickListener {
                // 북마크 상태 반전
                actionItem.isBookmark = !actionItem.isBookmark
                // 어댑터를 통해 해당 아이템만 갱신
                adapter.notifyItemChanged(bindingAdapterPosition)  // 변경된 부분
            }

            itemView.setOnClickListener {
                val currentClickTime = System.currentTimeMillis()
                if (currentClickTime - lastClickTime < doubleClickTime) {
                    // 두 번 클릭 처리 (필요한 경우 구현)
                } else {
                    lastClickTime = currentClickTime
                    val context = itemView.context
                    val intent = Intent(context, PlaybackActivity::class.java)
                    intent.putExtra("motion_name", actionItem.name)             // 동작 이름 전달
                    intent.putExtra("details_name", actionItem.detailsName)     // 세부 이름 전달
                    intent.putExtra("activity_details", actionItem.activityDetails) // 세부 정보 전달
                    context.startActivity(intent) // `PlaybackActivity` 실행
                }
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerText: TextView = itemView.findViewById(R.id.header_title)

        fun bind(headerItem: ActionItem.Header) {
            headerText.text = headerItem.title
        }
    }
}