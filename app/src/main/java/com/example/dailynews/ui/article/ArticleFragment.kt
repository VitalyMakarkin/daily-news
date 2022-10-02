package com.example.dailynews.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dailynews.databinding.FragmentArticleBinding
import com.example.dailynews.ui.articles.ArticlesFragment
import com.example.shared.presentation.ArticleViewModel.Companion.ARG_ARTICLE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    companion object {
        fun create(articleId: Int): ArticlesFragment {
            return ArticlesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ARTICLE_ID, articleId)
                }
            }
        }
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}