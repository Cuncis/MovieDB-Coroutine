package com.gdc.moviedbcoroutine.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.gdc.moviedbcoroutine.R
import com.gdc.moviedbcoroutine.data.model.FavoriteModel
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.android.synthetic.main.fragment_favorite.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment(), FavoriteAdapter.RemoveClickListener {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    private var favoriteList: ArrayList<FavoriteModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        initRecyclerView(view)

        observeViewModel()

    }

    private fun initRecyclerView(view: View) {
        favoriteAdapter = FavoriteAdapter(activity!!)
        favoriteAdapter.apply {
            setRemoveClickListener(this@FavoriteFragment)
        }
        view.rv_favorite.apply {
            layoutManager = LinearLayoutManager(activity!!)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

    private fun observeViewModel() {
        favoriteViewModel.getAllFavorites().observe(activity!!, Observer { list ->
            favoriteList.clear()
            favoriteList.addAll(list)
            favoriteAdapter.setFavoriteList(list)
        })

    }

    override fun onRemoveClick(position: Int) {
        favoriteViewModel.removeFavorite(favoriteList[position])
        favoriteAdapter.removeFavorite(position)
    }

}
