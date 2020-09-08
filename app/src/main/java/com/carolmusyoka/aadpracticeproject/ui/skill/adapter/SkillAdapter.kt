package com.carolmusyoka.aadpracticeproject.ui.skill.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carolmusyoka.aadpracticeproject.R
import com.carolmusyoka.aadpracticeproject.data.model.LearningHoursItem
import com.carolmusyoka.aadpracticeproject.data.model.SkillItem
import com.carolmusyoka.aadpracticeproject.ui.learning.adapter.LearningAdapter
import kotlinx.android.synthetic.main.item_learning.view.*

class SkillAdapter: RecyclerView.Adapter<SkillAdapter.SkillViewHolder>() {

    private val skill = ArrayList<SkillItem>()
    class SkillViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView)  {

        fun bind(skillItem: SkillItem ) {
            itemView.apply {
                leaderName.text = skillItem.name
                leaderCountry.text = skillItem.country
                leaderHours.text = skillItem.score.toString()
                Glide.with(leaderImage.context)
                    .load(skillItem.badgeUrl)
                    .into(leaderImage)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder =

        SkillViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
        )

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    override fun onBindViewHolder(holder: SkillViewHolder, position: Int) {
        holder.bind(skill[position])
    }

    override fun getItemCount(): Int {
        return skill.size
    }

    fun addSkillLeaders(skillLead : List<SkillItem>) {
        this.skill.apply {
            clear()
            addAll(skillLead)
        }

    }
}