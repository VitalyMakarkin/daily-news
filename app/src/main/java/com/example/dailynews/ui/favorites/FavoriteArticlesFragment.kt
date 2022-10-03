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
import com.example.dailynews.Screens
import com.example.dailynews.databinding.FragmentFavoriteArticlesBinding
import com.example.dailynews.ui.shared.ArticlesAdapter
import com.example.shared.presentation.FavoriteArticlesViewModel
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteArticlesFragment : Fragment(), ArticlesAdapter.ItemHandler {

    private val viewModel: FavoriteArticlesViewModel by viewModels()

    @Inject
    lateinit var router: Router

    private var _binding: FragmentFavoriteArticlesBinding? = null
    private val binding get() = _binding!!

    private val articlesAdapter: ArticlesAdapter by lazy { ArticlesAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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

        binding.refreshButton.setOnClickListener {
            viewModel.refresh()
        }

        viewModel.uiStateLiveData
            .distinctUntilChanged()
            .observe(viewLifecycleOwner) { render(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.uiStateLiveData.value is FavoriteArticlesViewModel.UiStateView.Data) {
            viewModel.refresh()
        }
    }

    override fun onItemClicked(articleId: Int) {
        router.navigateTo(Screens.toArticle(articleId))
    }

    override fun onFavoriteItemMarked(articleId: Int) {
        Timber.d("Not implemented")
    }

    override fun onFavoriteItemUnmarked(articleId: Int) {
        viewModel.removeArticleFromFavorites(articleId)
    }

    private fun render(uiStateView: FavoriteArticlesViewModel.UiStateView) {
        binding.articlesRv.isVisible = uiStateView is FavoriteArticlesViewModel.UiStateView.Data
        binding.errorsLayout.isVisible = uiStateView is FavoriteArticlesViewModel.UiStateView.Error
        binding.progressIndicator.isVisible =
            uiStateView is FavoriteArticlesViewModel.UiStateView.Loading

        when (uiStateView) {
            is FavoriteArticlesViewModel.UiStateView.Data -> {
                articlesAdapter.submitList(uiStateView.favoriteArticles)
            }
            is FavoriteArticlesViewModel.UiStateView.Error -> {
                binding.errorsTv.text = uiStateView.throwable.message ?: "Unknown Error!"
            }
            is FavoriteArticlesViewModel.UiStateView.Loading -> Unit
        }
    }
}