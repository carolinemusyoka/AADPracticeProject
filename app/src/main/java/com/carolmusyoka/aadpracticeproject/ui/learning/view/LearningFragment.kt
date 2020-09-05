package com.carolmusyoka.aadpracticeproject.ui.learning.view

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
import com.carolmusyoka.aadpracticeproject.data.model.LearningHoursItem
import com.carolmusyoka.aadpracticeproject.ui.learning.adapter.LearningAdapter
import com.carolmusyoka.aadpracticeproject.ui.learning.viewmodel.LearningViewModel
import com.carolmusyoka.aadpracticeproject.ui.learning.viewmodel.ViewModelFactory
import com.carolmusyoka.aadpracticeproject.utils.Status
import kotlinx.android.synthetic.main.fragment_learning.*


class LearningFragment : Fragment() {
    private lateinit var adapter: LearningAdapter
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: LearningViewModel

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
        return inflater.inflate(R.layout.fragment_learning, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_learning.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
        }
        adapter = LearningAdapter()
        recycler_learning.adapter = adapter
    }


    companion object{
        fun newInstance(): LearningFragment = LearningFragment()
    }

    private fun retrieveList(learningLead : List<LearningHoursItem>) {
        adapter.apply {
            addLearningHours(learningLead)
            notifyDataSetChanged()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(LearningViewModel::class.java)
    }


    private fun setupObservers() {
        viewModel.getLearningLeaders().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recycler_learning.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { heroResponse
                            -> retrieveList(heroResponse) }
                    }
                    Status.ERROR -> {
                        recycler_learning.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recycler_learning.visibility = View.GONE
                    }
                }
            }
        })
    }

}




