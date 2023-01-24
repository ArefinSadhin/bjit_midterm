package com.sadhin.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.sadhin.news.databinding.FragmentHomeBinding
import com.sadhin.news.viewmodel.NewsViewModel
import androidx.lifecycle.ViewModelProvider

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        val layoutManager = LinearLayoutManager(context)

        recyclerView = binding.recyclerNewsView
        recyclerView.layoutManager = layoutManager

        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setText(TOP_NEWS))
        tabLayout.addTab(tabLayout.newTab().setText(GENERAL))
        tabLayout.addTab(tabLayout.newTab().setText(BUSINESS))
        tabLayout.addTab(tabLayout.newTab().setText(ENTERTAINMENT))
        tabLayout.addTab(tabLayout.newTab().setText(SPORTS))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> {
                        viewModel.setList(TOP_NEWS)
                    }
                    1 -> {
                        viewModel.setList(GENERAL)
                    }
                    2 -> {
                        viewModel.setList(BUSINESS)
                    }
                    3 -> {
                        viewModel.setList(ENTERTAINMENT)
                    }
                    else -> {
                        viewModel.setList(SPORTS)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        val adapter = NewsAdapter(requireContext(), viewModel)
        recyclerView.adapter=adapter
        viewModel.list.observe(viewLifecycleOwner) { adapter.setData(it) }


    }

    /*private fun setRecyclerView(){
        viewModel.readArticles.observe(viewLifecycleOwner, Observer {
            binding.recyclerNewsView.adapter = NewsAdapter(viewModel.readArticles.value!!, viewModel)
        })
    }*/
    companion object {
        const val TOP_NEWS = "top_news"
        const val GENERAL = "general"
        const val BUSINESS = "business"
        const val ENTERTAINMENT = "entertainment"
        const val SPORTS = "sports"
        const val HEALTH = "health"
        const val TECHNOLOGY = "technology"
    }
}