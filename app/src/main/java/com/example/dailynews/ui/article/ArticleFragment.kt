package com.example.dailynews.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dailynews.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModels()
    private lateinit var binding: FragmentArticleBinding
    private val articleKey get() = requireArguments().getString(EXTRA_ARTICLE_KEY)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setArticleByKey(articleKey!!)
        with(viewModel.article.value!!) {
            // TODO("Bind image")
            binding.articleTitleTv.text = title
            binding.articleAuthorTv.text = author
            binding.articlePublishedAtTv.text = publishedAt
            binding.articleDescriptionTv.text = description
        }
    }

    companion object {
        private const val EXTRA_ARTICLE_KEY = "extra_article_key"

        @JvmStatic
        fun newInstance(articleKey: String): Fragment {
            return ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ARTICLE_KEY, articleKey)
                }
            }
        }
    }
}
