package com.gdc.moviedbcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.ui.FavoriteFragment
import com.gdc.moviedbcoroutine.ui.NowPlayingFragment
import com.gdc.moviedbcoroutine.ui.UpcomingFragment
import com.gdc.moviedbcoroutine.util.TabAdapter
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: MainAdapter
    lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(NowPlayingFragment(), "Now Playing")
        tabAdapter.addFragment(UpcomingFragment(), "Upcoming")
        tabAdapter.addFragment(FavoriteFragment(), "Favorite")
        pager.adapter = tabAdapter
        tab_layout.setupWithViewPager(pager)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRecyclerView()
        observeViewModel()

        rv_nowPlaying.visibility = View.GONE
    }

    private fun initRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf())
        rv_nowPlaying.layoutManager = LinearLayoutManager(this)
        rv_nowPlaying.setHasFixedSize(true)
        rv_nowPlaying.adapter = mainAdapter
    }

    private fun observeViewModel() {
        mainViewModel.getNowPlayingMovieList().observe(this, Observer { nowPlayingList ->
            Utility.showLog("Data: $nowPlayingList")
            mainAdapter.setNowPlayingList(nowPlayingList)
        })

        mainViewModel.getLoadError().observe(this, Observer {
            tv_errorMessage.text = it
        })

        mainViewModel.onLoading().observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }
}
