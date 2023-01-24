package com.sadhin.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sadhin.news.databinding.FragmentBookMarkBinding
import com.sadhin.news.viewmodel.BookMarkViewModel
import com.sadhin.news.viewmodel.NewsViewModel


class BookMarkFragment : Fragment() {
    private var _binding: FragmentBookMarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BookMarkViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_book_mark, container, false)
        _binding = FragmentBookMarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)[BookMarkViewModel::class.java]
        val layoutManager= LinearLayoutManager(context)
        val adapter=BookMarkAdapter(requireContext(),viewModel)

        recyclerView=binding.recyclerNewsView
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=adapter
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        Log.d("bookmark", "onViewCreated: ${viewModel.list.value}")
    }


    companion object {

    }
}