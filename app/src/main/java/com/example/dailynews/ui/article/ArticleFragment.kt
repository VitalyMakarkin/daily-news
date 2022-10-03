package com.example.dailynews.ui.article

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.dailynews.databinding.FragmentArticleBinding
import com.example.shared.presentation.ArticleViewModel
import com.example.shared.presentation.ArticleViewModel.Companion.ARG_ARTICLE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    companion object {
        fun create(articleId: Int): ArticleFragment {
            return ArticleFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ARTICLE_ID, articleId)
                }
            }
        }
    }

    private val viewModel: ArticleViewModel by viewModels()

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshButton.setOnClickListener {
            viewModel.refresh()
        }

        viewModel.uiStateLiveData.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(uiStateView: ArticleViewModel.UiStateView) {
        binding.articleLayout.isVisible = uiStateView is ArticleViewModel.UiStateView.Data
        binding.errorsLayout.isVisible = uiStateView is ArticleViewModel.UiStateView.Error
        binding.progressIndicator.isVisible = uiStateView is ArticleViewModel.UiStateView.Loading

        when (uiStateView) {
            is ArticleViewModel.UiStateView.Data -> {
                val article = uiStateView.article
                binding.articleTitleTv.text = article.title
                binding.articleAuthorTv.text = article.author
                binding.articlePublishedAtTv.text = article.publishedAt
                binding.articleDescriptionTv.text = article.description
                binding.articleImageIv.load(article.urlToImage) {
                    placeholder(ColorDrawable(0xEEEEEE))
                    crossfade(true)
                }
            }
            is ArticleViewModel.UiStateView.Error -> {
                binding.errorsTv.text = uiStateView.throwable.message ?: "Unknown Error!"
            }
            is ArticleViewModel.UiStateView.Loading -> Unit
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}