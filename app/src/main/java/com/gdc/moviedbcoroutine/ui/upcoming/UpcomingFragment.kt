package com.gdc.moviedbcoroutine.ui.upcoming

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.gdc.moviedbcoroutine.R
import com.gdc.moviedbcoroutine.data.model.Upcoming
import com.gdc.moviedbcoroutine.ui.now_playing.detail.NowPlayingDetailActivity
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.android.synthetic.main.fragment_upcoming.view.*

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment(), UpcomingAdapter.DetailClickListener {

    private lateinit var upcomingViewModel: UpcomingViewModel
    private lateinit var upcomingAdapter: UpcomingAdapter
    private var movieList = ArrayList<Upcoming>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingViewModel = ViewModelProvider(activity!!).get(UpcomingViewModel::class.java)

        initRecyclerView(view)

        observeViewModel(view)

    }

    private fun initRecyclerView(view: View) {
        upcomingAdapter = UpcomingAdapter(arrayListOf())
        upcomingAdapter.setDetailClickListener(this)
        view.rv_upcoming.apply {
            layoutManager = LinearLayoutManager(activity!!)
            setHasFixedSize(true)
            adapter = upcomingAdapter
        }
    }

    private fun observeViewModel(view: View) {
        upcomingViewModel.getUpcomingMovies("in").observe(activity!!, Observer {
            movieList.addAll(it)
            upcomingAdapter.setUpcomingList(movieList)
        })

        upcomingViewModel.getErrorMessage().observe(activity!!, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        upcomingViewModel.onLoading().observe(activity!!, Observer {
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
        intent.putExtra("KEY_TYPE", "02")
        startActivity(intent)
    }

}
