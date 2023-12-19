package com.example.palace_resorts.flows.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.palace_resorts.base.FragmentView
import com.example.palace_resorts.databinding.FragmentFavoritesBinding
import com.example.palace_resorts.flows.models.Articles
import com.example.palace_resorts.flows.states.NewsActions
import com.example.palace_resorts.flows.viewmodel.FavoritesViewModel
import com.example.palace_resorts.flows.views.NewsItemView
import com.example.palace_resorts.utils.extensionUtils.hide
import com.example.palace_resorts.utils.extensionUtils.orZero
import com.example.palace_resorts.utils.extensionUtils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class FavoritesFragment : FragmentView() {

    private val binding by lazy {
        FragmentFavoritesBinding.inflate(layoutInflater)
    }

    private val viewModel: FavoritesViewModel by activityViewModels()

    private var listener: FavoritesFragmentListener? = null

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? FavoritesFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener?.showToolBar()
        setupRecyclerView()
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.apply {
            getShowProgress().observe(viewLifecycleOwner, ::showLoading)
            getShowErrorMessageText().observe(viewLifecycleOwner, ::showError)
            getAction().observe(viewLifecycleOwner, ::handleAction)
            getAllNews()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
        }
    }

    private fun handleAction(action: NewsActions) {
        binding.apply {
            if (action is NewsActions.GetAllNews) {
                textViewError.hide()
                recyclerView.show()
                val news = mutableListOf<Articles>()
                action.newsList.map {
                    news.add(
                        Articles(
                            it?.id.orZero(),
                            it?.author.orEmpty(),
                            it?.title.orEmpty(),
                            it?.urlToImage.orEmpty(),
                            it?.publishedAt.orEmpty(),
                            it?.content.orEmpty()
                        )
                    )
                }
                showNews(news)
            } else if (action is NewsActions.ShowEmptyFavorites) {
                recyclerView.hide()
                textViewError.show()
            }
        }
    }

    private fun createItem(article: Articles?): NewsItemView {
        return NewsItemView(article) {
            it.let {
                listener?.navigateToFavoriteDetail(it)
            }
        }
    }

    private fun showNews(news: List<Articles>) {
        binding.apply {
            groupAdapter.clear()
            groupAdapter.addAll(news.map { createItem(it) })
        }
    }

    interface FavoritesFragmentListener {
        fun showToolBar()
        fun navigateToFavoriteDetail(article: Articles)
    }
}