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
import com.example.dailynews.Screens
import com.example.dailynews.databinding.FragmentArticleBinding
import com.example.shared.presentation.ArticleViewModel
import com.example.shared.presentation.ArticleViewModel.Companion.ARG_ARTICLE_ID
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

    @Inject
    lateinit var router: Router

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
                binding.articleAuthorTv.visibility = when {
                    article.author.isEmpty() && article.author.isBlank() -> View.GONE
                    else -> View.VISIBLE
                }

                binding.articlePublishedAtTv.text = article.publishedAt

                binding.articleDescriptionTv.text = article.description
                binding.articleDescriptionTv.visibility = when {
                    article.description.isEmpty() && article.description.isBlank() -> View.GONE
                    else -> View.VISIBLE
                }

                binding.articleImageIv.load(article.urlToImage) {
                    placeholder(ColorDrawable(0xEEEEEE))
                    crossfade(true)
                }
                binding.articleImageIv.visibility = when {
                    article.urlToImage.isEmpty() && article.urlToImage.isBlank() -> View.GONE
                    else -> View.VISIBLE
                }

                binding.favoriteMarkedIv.setOnClickListener {
                    viewModel.removeArticleFromFavorites(article.id)
                }
                binding.favoriteMarkedIv.visibility = when (article.isFavorite) {
                    true -> View.VISIBLE
                    else -> View.GONE
                }

                binding.favoriteUnmarkedIv.setOnClickListener {
                    viewModel.addArticleToFavorites(article.id)
                }
                binding.favoriteUnmarkedIv.visibility = when (article.isFavorite) {
                    true -> View.GONE
                    else -> View.VISIBLE
                }

                binding.backNavIv.setOnClickListener {
                    router.exit()
                }

                binding.sourceLinkIv.setOnClickListener {
                    router.navigateTo(Screens.toArticleSource(article.url))
                }
                binding.sourceLinkIv.visibility = when {
                    article.url.isEmpty() && article.url.isBlank() -> View.GONE
                    else -> View.VISIBLE
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