package ui.smartpro.a22byte.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ui.smartpro.a22byte.R
import ui.smartpro.a22byte.databinding.FragmentFavouriteBinding
import ui.smartpro.a22byte.ui.activity.MainActivity
import ui.smartpro.a22byte.ui.main.MainViewModel
import ui.smartpro.common.base.BaseFragment
import ui.smartpro.a22byte.ui.adapter.NewsAdapter

class FavouriteFragment(
    override val layoutId: Int = R.layout.fragment_favourite
) : BaseFragment<FragmentFavouriteBinding>() {

    lateinit var viewModel: MainViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun initViews(view: View) {
        setupRecyclerView()
        setupUI(view)
    }

    override fun initViewModel() {
        viewModel = (activity as MainActivity).mainViewModel
        setupObserver()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvFavoriteNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun setupUI(view: View) {
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("news", it)
                putBoolean("isFromFavorite", true)
            }
            findNavController().navigate(
                R.id.action_favouriteFragment_to_detailsFragment,
                bundle
            )
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteNews(article)
                Snackbar.make(
                    view,
                    getString(R.string.favourite_delete_successful),
                    Snackbar.LENGTH_LONG
                )
                    .apply {
                        setAction(getString(R.string.undo)) {
                            viewModel.saveNews(article)
                        }
                        show()
                    }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.rvFavoriteNews)
        }
    }

    private fun setupObserver() {
        doInScope {
            viewModel.getFavoriteNews().observe(viewLifecycleOwner) { news ->
                newsAdapter.differ.submitList(news)
            }
        }
    }
}