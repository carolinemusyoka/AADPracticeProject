package com.carolmusyoka.aadpracticeproject.ui.skill.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carolmusyoka.aadpracticeproject.R
import com.carolmusyoka.aadpracticeproject.data.model.SkillItem
import com.carolmusyoka.aadpracticeproject.ui.skill.adapter.SkillAdapter
import kotlinx.android.synthetic.main.fragment_skill.*

/**
 * A simple [Fragment] subclass.
 */
class SkillFragment : Fragment() {
    private lateinit var adapter: SkillAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_skill.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }
        adapter = SkillAdapter()
        recycler_skill.adapter = adapter
    }


    companion object{
    }

    private fun retrieveList(skillLead : List<SkillItem>) {
        adapter.apply {
            addSkillLeaders(skillLead)
            notifyDataSetChanged()
        }
    }


}
