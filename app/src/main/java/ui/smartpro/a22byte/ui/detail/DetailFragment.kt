package ui.smartpro.a22byte.ui.detail

import android.view.View
import android.webkit.WebViewClient
import androidx.core.view.isGone
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import ui.smartpro.a22byte.R
import ui.smartpro.a22byte.databinding.FragmentDetailBinding
import ui.smartpro.a22byte.ui.activity.MainActivity
import ui.smartpro.a22byte.ui.main.MainViewModel
import ui.smartpro.common.base.BaseFragment

class DetailFragment(
    override val layoutId: Int = R.layout.fragment_detail
) : BaseFragment<FragmentDetailBinding>() {

    lateinit var viewModel: MainViewModel
    val args: DetailFragmentArgs by navArgs()

    override fun initViews(view: View) {
        setupUI(view)
    }

    override fun initViewModel() {
        viewModel = (activity as MainActivity).mainViewModel
        setupObserver()
    }

    private fun setupUI(view: View) {
        val news = args.news
        binding.webView.apply {
            webViewClient = WebViewClient()
            news.url?.let {
                loadUrl(it)
            }
        }

        binding.fab.setOnClickListener {
            viewModel.saveNews(news)
            Snackbar.make(view, getString(R.string.saved_favorites_article), Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private fun setupObserver() {
        doInScope {
            viewModel.getFavoriteNews().observe(viewLifecycleOwner) { news ->
                binding.fab.isGone = news.any { it.title == args.news.title }
            }
        }
    }
}