package com.gdc.moviedbcoroutine.ui.now_playing.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gdc.moviedbcoroutine.BuildConfig
import com.gdc.moviedbcoroutine.R
import com.gdc.moviedbcoroutine.data.model.NowPlayingDetailResponse
import com.gdc.moviedbcoroutine.util.Utility
import com.gdc.moviedbcoroutine.util.Utility.Companion.loadImage
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_now_playing_detail.*

class NowPlayingDetailActivity : AppCompatActivity() {

    private lateinit var nowPlayingDetailViewModel: NowPlayingDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_now_playing_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbar.title = intent.getStringExtra("KEY_NAME")

        collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white))

        nowPlayingDetailViewModel = ViewModelProvider(this).get(NowPlayingDetailViewModel::class.java)

        tv_title.text = intent.getStringExtra("KEY_NAME")

        observeViewModel()
    }

    private fun observeViewModel() {
        nowPlayingDetailViewModel.getNowPlayingDetail(intent.getIntExtra("KEY_MOVIE_ID", 0), "in")
            .observe(this, Observer {
                setNowPlayingDetail(it)
            })
    }

    private fun setNowPlayingDetail(detail: NowPlayingDetailResponse) {
        iv_posterMovie.loadImage(BuildConfig.IMG_URL + "w500" + detail.backdropPath)
        img_poster.loadImage(BuildConfig.IMG_URL + "w500" + detail.posterPath)
        tv_release_date.text = detail.releaseDate
        tv_vote.text = detail.voteAverage.toString()
        tv_genres.text = detail.genres?.get(0)?.name
        tv_overview.text = detail.overview
        img_poster_belongs.loadImage(BuildConfig.IMG_URL + "w500" + detail.productionCompanies!![0].logoPath)
        tv_title_belongs.text = detail.belongsToCollection
        tv_budget.text = detail.budget.toString()
        tv_revenue.text = detail.revenue.toString()
        tv_companies.text = detail.productionCompanies[0].name
        tv_countries.text = detail.productionCountries!![0].name
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
