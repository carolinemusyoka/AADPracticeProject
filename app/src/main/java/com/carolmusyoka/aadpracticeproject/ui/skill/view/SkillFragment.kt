package com.carolmusyoka.aadpracticeproject.ui.skill.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carolmusyoka.aadpracticeproject.R
import com.carolmusyoka.aadpracticeproject.data.api.ApiHelper
import com.carolmusyoka.aadpracticeproject.data.api.RetrofitBuilder
import com.carolmusyoka.aadpracticeproject.data.model.SkillItem
import com.carolmusyoka.aadpracticeproject.ui.skill.adapter.SkillAdapter
import com.carolmusyoka.aadpracticeproject.ui.skill.viewmodel.SkillViewModel
import com.carolmusyoka.aadpracticeproject.ui.skill.viewmodel.ViewModelFactory
import com.carolmusyoka.aadpracticeproject.utils.Status
import kotlinx.android.synthetic.main.fragment_skill.*

/**
 * A simple [Fragment] subclass.
 */
class SkillFragment : Fragment() {
    private lateinit var adapter: SkillAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SkillViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setupViewModel()
        setupObservers()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        fun newInstance(): SkillFragment = SkillFragment()
    }

    private fun retrieveList(skillLead : List<SkillItem>) {
        adapter.apply {
            addSkillLeaders(skillLead)
            notifyDataSetChanged()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(SkillViewModel::class.java)
    }


    private fun setupObservers() {
        viewModel.getLearningLeaders().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recycler_skill.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { skillLead
                            -> retrieveList(skillLead) }
                    }
                    Status.ERROR -> {
                        recycler_skill.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recycler_skill.visibility = View.GONE
                    }
                }
            }
        })
    }

}




