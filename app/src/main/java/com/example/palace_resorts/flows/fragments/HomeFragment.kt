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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.showToolBar()
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

    private fun showNews(newsList: NewsResponse) {
        groupAdapter.clear()
        groupAdapter.addAll(
            newsList.articlesList.mapIndexed { index, item ->
                createItem(item, index + 1)
            }
        )
    }

    private fun createItem(article: Articles?, index: Int): NewsItemView {
        return NewsItemView(article, index) {
            it.let {

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
                    groupAdapter.clear()
                    articles?.let { article ->
                        val searchList: List<Articles> = article.articlesList.filter {
                            text.toString().lowercase().contains(it.author.toString().take(4).lowercase())
                        }
                        if (searchList.isNotEmpty()) {
                            recyclerView.show()
                            groupAdapter.addAll(
                                searchList.mapIndexed { index, item ->
                                    createItem(item, index)
                                }
                            )
                        } else {
                            recyclerView.hide()
                            textViewError.show()
                        }
                    }
                } else {
                    articles?.let { news ->
                        showNews(news)
                    }
                }
            }
        }
    }

    interface HomeFragmentListener {
        fun showToolBar()
    }
}