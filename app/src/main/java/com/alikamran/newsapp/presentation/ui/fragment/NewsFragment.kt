package com.alikamran.newsapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alikamran.newsapp.R
import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.data.util.Resource
import com.alikamran.newsapp.databinding.FragmentNewsBinding
import com.alikamran.newsapp.presentation.adapter.NewsAdapter
import com.alikamran.newsapp.presentation.viewmodel.NewsViewModel
import com.alikamran.newsapp.presentation.ui.activities.MainActivity


class NewsFragment : Fragment() {


    private lateinit var viewModel: NewsViewModel
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0
    private  var searchQuery: String = ""
    private  var newsList =  arrayListOf<Article>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_news, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(
                R.id.action_newsFragment_to_infoFragment,
                bundle
            )
        }

        initRecyclerView()
        setSearchView()
        viewSearchedNews()
    }


    private fun initRecyclerView() {
        newsAdapter.differ.submitList(null)
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollingListener)
        }

    }

    private fun showProgressBar() {
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }


    private val onScrollingListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManger = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManger.itemCount
            val visibleItems = layoutManger.childCount
            val topPosition = layoutManger.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList

            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling

            if (shouldPaginate) {
                page++
                viewModel.searchNews(searchQuery, page)
                isScrolling = false
            }

        }
    }

    private fun setSearchView() {


        fragmentNewsBinding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                newsList.clear()
                viewModel.searchNews(p0.toString(), page)
                searchQuery = p0.toString()
                viewSearchedNews()
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                return false
            }

        })
        fragmentNewsBinding.svNews.setOnCloseListener {
            initRecyclerView()
            viewSearchedNews()
            false
        }


    }

    fun viewSearchedNews() {

        viewModel.searchedNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        newsList.addAll(it.articles.toList())
                        newsAdapter.differ.submitList(newsList)

                        pages = if (it.totalResults % 20 == 0) {
                            it.totalResults / 20
                        } else {
                            it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
//                    showNotFoundLayout()
                }

                is Resource.Loading -> {
                    showProgressBar()
//                    hideNotFoundLayout()

                }


            }
        }
    }

}