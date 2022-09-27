package com.example.dailynews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynews.databinding.FragmentArticlesBinding
import com.example.dailynews.databinding.FragmentFavoriteArticlesBinding
import com.example.dailynews.ui.shared.ArticlesAdapter
import com.example.shared.presentation.ArticlesViewModel
import com.example.shared.presentation.FavoriteArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FavoriteArticlesFragment : Fragment(), ArticlesAdapter.ItemHandler {
    private val viewModel: FavoriteArticlesViewModel by viewModels()

    private var _binding: FragmentFavoriteArticlesBinding? = null
    private val binding get() = _binding!!

    private val articlesAdapter: ArticlesAdapter by lazy { ArticlesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.articlesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articlesAdapter
            setHasFixedSize(true)
        }

        viewModel.uiStateLiveData
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) { render(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(articleId: Int) {
        Timber.d("onItemClicked()")
    }

    override fun onFavoriteItemMarked(articleId: Int) {
        Timber.e("Not implemented")
    }

    override fun onFavoriteItemUnmarked(articleId: Int) {
        viewModel.removeArticleFromFavorites(articleId)
    }

    private fun render(uiStateView: FavoriteArticlesViewModel.UiStateView) {
        binding.progressIndicator.isVisible = uiStateView is FavoriteArticlesViewModel.UiStateView.Loading
        binding.articlesRv.isVisible = uiStateView is FavoriteArticlesViewModel.UiStateView.Data
        binding.errorsLayout.isVisible = uiStateView is FavoriteArticlesViewModel.UiStateView.Error

        when (uiStateView) {
            is FavoriteArticlesViewModel.UiStateView.Loading -> {
                // TODO: Implement view
            }
            is FavoriteArticlesViewModel.UiStateView.Data -> {
                articlesAdapter.submitList(uiStateView.favoriteArticles)
            }
            is FavoriteArticlesViewModel.UiStateView.Error -> {
                // TODO: Implement view
            }
        }
    }
}