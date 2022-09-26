package com.example.dailynews.ui.settings

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shared.presentation.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private val viewModel: SettingsViewModel by viewModels()
}