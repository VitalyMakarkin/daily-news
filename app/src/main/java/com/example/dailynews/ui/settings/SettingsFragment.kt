package com.example.dailynews.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dailynews.databinding.FragmentSettingsBinding
import com.example.shared.presentation.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val viewModel: SettingsViewModel by viewModels()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { uiStateView ->
            when (uiStateView) {
                is SettingsViewModel.UiStateView.Loading -> {
                    binding.cachedCountTv.text = "-"
                    binding.favoritesCountTv.text = "-"
                }
                is SettingsViewModel.UiStateView.Data -> {
                    binding.cachedCountTv.text = uiStateView.cachedArticlesCount.toString()
                    binding.favoritesCountTv.text = uiStateView.favoriteArticlesCount.toString()
                }
                is SettingsViewModel.UiStateView.Error -> {
                    binding.cachedCountTv.text = "Error"
                    binding.favoritesCountTv.text = "Error"
                }
            }
        }

        binding.cachedCountLayout.setOnLongClickListener {
            viewModel.removeCachedArticles()
            true
        }

        binding.favoritesCountLayout.setOnLongClickListener {
            viewModel.removeFavoriteArticles()
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}