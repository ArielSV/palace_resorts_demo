package com.example.palace_resorts.flows.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.palace_resorts.R
import com.example.palace_resorts.base.FragmentView
import com.example.palace_resorts.databinding.FragmentNewsDetailBinding
import com.example.palace_resorts.flows.models.EntityNewsItem
import com.example.palace_resorts.flows.states.NewsActions
import com.example.palace_resorts.flows.viewmodel.NewsDetailViewModel
import com.example.palace_resorts.utils.date.DateUtils
import com.example.palace_resorts.utils.extensionUtils.orZero
import com.example.palace_resorts.utils.imageUtils.loadImages

class NewsDetailFragment : FragmentView() {

    private val binding by lazy {
        FragmentNewsDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: NewsDetailViewModel by activityViewModels()

    private val args: NewsDetailFragmentArgs by navArgs()

    private var listener: NewsDetailFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewsDetailFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.apply {
            getAction().observe(viewLifecycleOwner, ::handelAction)
            getShowProgress().observe(viewLifecycleOwner, ::showLoading)
            getShowErrorMessageText().observe(viewLifecycleOwner, ::showError)
        }
    }

    private fun handelAction(action: NewsActions) {
        if (action is NewsActions.OnDeleteSuccess) {
            onBackPressed()
        } else if (action is NewsActions.OnAddedSuccess) {
            Toast.makeText(
                requireContext(),
                requireContext().getText(R.string.favorites_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupView() {
        listener?.hideToolBar()
        binding.apply {
            loadImages(requireContext(), args.uiModel.urlToImage.orEmpty(), imageViewIcon)
            textViewName.text = args.uiModel.author
            textViewDate.text = DateUtils.formatDate(args.uiModel.publishedAt.orEmpty())
            textViewDescription.text = args.uiModel.content
            imageViewBack.setOnClickListener { onBackPressed() }
            textViewTitle.text = args.uiModel.author.orEmpty()
            imageViewRightAction.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    if (args.isFromRemote) {
                        R.drawable.ic_favorite
                    } else {
                        R.mipmap.icon_trash
                    }
                )
            )
            imageViewRightAction.setOnClickListener {
                if (args.isFromRemote) {
                    viewModel.saveNew(args.uiModel)
                } else {
                    viewModel.deleteNew(
                        EntityNewsItem(
                            id = args.uiModel.id.orZero(),
                            author = args.uiModel.author.orEmpty(),
                            title = args.uiModel.title.orEmpty(),
                            urlToImage = args.uiModel.urlToImage.orEmpty(),
                            publishedAt = args.uiModel.publishedAt.orEmpty(),
                            content = args.uiModel.content.orEmpty()
                        )
                    )
                }
            }

        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface NewsDetailFragmentListener {
        fun hideToolBar()
    }
}