package com.carolmusyoka.aadpracticeproject.ui.learning.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carolmusyoka.aadpracticeproject.R
import com.carolmusyoka.aadpracticeproject.data.model.LearningHoursItem
import kotlinx.android.synthetic.main.item_learning.view.*

class LearningAdapter: RecyclerView.Adapter<LearningAdapter.LearningViewHolder>() {

    private val learning = ArrayList<LearningHoursItem>()
    class LearningViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView)  {

        fun bind(learningHoursItem: LearningHoursItem ) {
            itemView.apply {
                leaderName.text = learningHoursItem.name
                leaderCountry.text = learningHoursItem.country
                leaderHours.text = learningHoursItem.hours.toString()
                Glide.with(leaderImage.context)
                    .load(learningHoursItem.badgeUrl)
                    .into(leaderImage)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningViewHolder =

        LearningViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_learning, parent, false)
        )

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    override fun onBindViewHolder(holder: LearningViewHolder, position: Int) {
        holder.bind(learning[position])
    }

    override fun getItemCount(): Int {
        return learning.size
    }

    fun addLearningHours(learningLead : List<LearningHoursItem>) {
        this.learning.apply {
            clear()
            addAll(learningLead)
        }

    }
}