package com.example.dignews.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dignews.R
import com.example.dignews.adapters.NewsAdapter
import com.example.dignews.viewmodels.NewsViewModel
import com.example.trendnews.utils.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel:NewsViewModel
    private lateinit var newsAdapter:NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(NewsViewModel::class.java)
        setUpRecyclerview()


        newsAdapter.setOnItemCLickListener{article->
            val bundle = Bundle().apply {
                putSerializable("article", article)
            }

            findNavController().navigate(
                R.id.action_breakingNewsFragment4_to_articleFragment2,
                bundle
            )

        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        showProgressBar()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }


/////////////////////////////////////////method to hide progress bar////////////////////////////////
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }
////////////////////////////////////method to show progress bar/////////////////////////////////////
  private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerview() {
        newsAdapter = NewsAdapter()
        rvbreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }

    }