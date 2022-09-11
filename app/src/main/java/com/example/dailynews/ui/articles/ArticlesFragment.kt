package com.example.dailynews.ui.articles

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
import com.example.shared.presentation.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : Fragment() {

    private val viewModel: ArticlesViewModel by viewModels()
    private lateinit var binding: FragmentArticlesBinding
    private val articlesAdapter: ArticlesAdapter by lazy { ArticlesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(inflater, container, false)
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

    private fun render(uiStateView: ArticlesViewModel.UiStateView) {
        // TODO: Implement visibility: uiStateView is ArticlesViewModel.UiStateView.Loading
        binding.articlesRv.isVisible = uiStateView is ArticlesViewModel.UiStateView.Data
        // TODO: Implement visibility: uiStateView is ArticlesViewModel.UiStateView.Error

        when (uiStateView) {
            is ArticlesViewModel.UiStateView.Loading -> {
                // TODO: Implement view
            }
            is ArticlesViewModel.UiStateView.Data -> {
                articlesAdapter.submitList(uiStateView.articles)
            }
            is ArticlesViewModel.UiStateView.Error -> {
                // TODO: Implement view
            }
        }
    }
}