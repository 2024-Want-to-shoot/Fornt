package com.example.a2024_want_to_shoot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RulesAdapter(private var rules: List<Rule>) :
    RecyclerView.Adapter<RulesAdapter.RuleViewHolder>() {

    private var filteredRules: List<Rule> = rules

    // ViewHolder 정의
    class RuleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val categoryText: TextView = view.findViewById(R.id.rule_category)
        val ruleNameText: TextView = view.findViewById(R.id.rule_name)
        val descriptionText: TextView = view.findViewById(R.id.rule_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rule, parent, false)
        return RuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RuleViewHolder, position: Int) {
        val rule = filteredRules[position]
//        holder.categoryText.text = rule.rulename
        holder.ruleNameText.text = rule.rulename
        holder.descriptionText.text = rule.description
    }

    override fun getItemCount(): Int = filteredRules.size

    fun filterByCategory(category: String) {
        filteredRules = if (category == "전체") {
            rules // 전체 보기일 경우 원본 리스트를 표시
        } else {
            rules.filter { it.category == category } // 해당 카테고리만 필터링
        }
        notifyDataSetChanged() // RecyclerView를 갱신
    }


}
