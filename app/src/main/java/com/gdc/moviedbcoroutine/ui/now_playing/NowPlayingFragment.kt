package com.gdc.moviedbcoroutine.ui.now_playing

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.gdc.moviedbcoroutine.R
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.ui.now_playing.detail.NowPlayingDetailActivity
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.android.synthetic.main.fragment_now_paying.view.*

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment(), NowPlayingAdapter.DetailClickListener {

    lateinit var nowPlayingViewModel: NowPlayingViewModel
    lateinit var nowPlayingAdapter: NowPlayingAdapter
    private var movieList = ArrayList<NowPlaying>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_now_paying, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)

        initRecyclerView(view)

        observeViewModel(view)
    }

    private fun initRecyclerView(view: View) {
        nowPlayingAdapter = NowPlayingAdapter(arrayListOf())
        view.rv_nowPlaying.layoutManager = LinearLayoutManager(activity)
        view.rv_nowPlaying.setHasFixedSize(true)
        nowPlayingAdapter.setDetailClickListener(this)
        view.rv_nowPlaying.adapter = nowPlayingAdapter
    }

    private fun observeViewModel(view: View) {
        nowPlayingViewModel.getNowPlayingMovieList().observe(activity!!, Observer { nowPlayingList ->
            movieList.addAll(nowPlayingList)
            Utility.showLog("Data: $nowPlayingList")
            nowPlayingAdapter.setNowPlayingList(nowPlayingList)
        })

        nowPlayingViewModel.getLoadError().observe(activity!!, Observer {
            view.tv_errorMessage.text = it
        })

        nowPlayingViewModel.onLoading().observe(activity!!, Observer {
            if (it) {
                view.progressBar.visibility = View.VISIBLE
            } else {
                view.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onDetailClick(position: Int) {
        val intent = Intent(activity, NowPlayingDetailActivity::class.java)
        intent.putExtra("KEY_NAME", movieList[position].originalTitle)
        intent.putExtra("KEY_MOVIE_ID", movieList[position].id)
        startActivity(intent)
    }

}
