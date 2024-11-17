package com.example.a2024_want_to_shoot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaylistAdapter(
    private val items: List<PlaylistItem>,
    private val onItemClick: (PlaylistItem) -> Unit // 클릭 리스너 추가
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        // 아이템 클릭 이벤트 처리
        holder.itemView.setOnClickListener {
            onItemClick(item) // 클릭된 아이템 전달
        }
    }

    override fun getItemCount(): Int = items.size

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(item: PlaylistItem) {
            title.text = item.title
            checkBox.isChecked = item.isChecked
        }
    }
}
