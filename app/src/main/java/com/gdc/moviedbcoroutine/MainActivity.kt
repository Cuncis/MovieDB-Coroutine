package com.gdc.moviedbcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.refresh()

        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.nowPlayingList.observe(this, Observer { nowPlayingList ->
            nowPlayingList.let {
                Log.d("_logMovie", "Data: $it")
            }
        })

        mainViewModel.loading.observe(this, Observer { isLoading ->
            isLoading.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
