package com.example.palace_resorts.flows.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.palace_resorts.base.FragmentView
import com.example.palace_resorts.databinding.FragmentHomeBinding
import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.flows.models.NewsResponse
import com.example.palace_resorts.flows.states.NewsActions
import com.example.palace_resorts.flows.viewmodel.HomeViewModel
import com.example.palace_resorts.flows.views.NewsItemView
import com.example.palace_resorts.utils.extensionUtils.hide
import com.example.palace_resorts.utils.extensionUtils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class HomeFragment : FragmentView() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private val viewModel: HomeViewModel by activityViewModels()

    private var listener: HomeFragmentListener? = null

    private var articles: NewsResponse? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? HomeFragmentListener
    }

    private fun handleAction(action: NewsActions) {
        when (action) {
            is NewsActions.OnSuccess -> {
                articles = action.newsList
                setupRecyclerView()
                showNews(action.newsList)
            }

            else -> Unit
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.hideToolbar()
        setupClickListener()
        bindViewModel()
        searchCustomer()
    }

    private fun bindViewModel() {
        viewModel.apply {
            getShowProgress().observe(viewLifecycleOwner, ::showLoading)
            getShowErrorMessageText().observe(viewLifecycleOwner, ::showError)
            getAction().observe(viewLifecycleOwner, ::handleAction)
            getNews()
        }
    }

    private fun createItem(article: Articles?): NewsItemView {
        return NewsItemView(article) {
            it.let {
                listener?.navigateToDetail(it)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
    }

    private fun searchCustomer() {
        binding.apply {
            editTextSearch.addTextChangedListener { text ->
                if (text.toString().length == 4) {
                    performSearch(text.toString())
                } else {
                    articles?.let { showNews(it) }
                }
            }
        }
    }

    private fun performSearch(query: String) {
        binding.apply {
            articles?.let { article ->
                val searchList = article.articlesList.filter {
                    query.lowercase()
                        .contains(it.author?.take(4)?.lowercase() ?: "")
                }

                if (searchList.isNotEmpty()) {
                    recyclerView.show()
                    groupAdapter.addAll(searchList.map { createItem(it) })
                } else {
                    recyclerView.hide()
                    textViewError.show()
                }
            }
        }
    }

    private fun showNews(news: NewsResponse) {
        binding.apply {
            recyclerView.show()
            textViewError.hide()
            groupAdapter.clear()
            groupAdapter.addAll(news.articlesList.map { createItem(it) })
        }
    }

    private fun setupClickListener() {
        binding.imageViewRightAction.setOnClickListener { listener?.navigateToFavorites() }
    }

    interface HomeFragmentListener {
        fun hideToolbar()
        fun navigateToDetail(article: Articles)
        fun navigateToFavorites()
    }
}