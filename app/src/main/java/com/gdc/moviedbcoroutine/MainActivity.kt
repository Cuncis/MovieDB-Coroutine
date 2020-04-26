package com.gdc.moviedbcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.data.model.NowPlayingResponse
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRecyclerView()
        observeViewModel()
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
