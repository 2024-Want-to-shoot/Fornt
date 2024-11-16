package com.example.a2024_want_to_shoot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookmarkAdapter(private val items: List<ActionItem.Action>) :
    RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_action, parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val action = items[position]
        holder.bind(action)
    }

    override fun getItemCount(): Int = items.size

    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actionName: TextView = itemView.findViewById(R.id.action_name)
        private val actionCategory: TextView = itemView.findViewById(R.id.action_category)
        private val actionBookmark: ImageView = itemView.findViewById(R.id.action_bookmark)

        fun bind(action: ActionItem.Action) {
            actionName.text = action.name
            actionCategory.text = action.category
            actionBookmark.setImageResource(R.drawable.ic_fillstar)
        }
    }
}