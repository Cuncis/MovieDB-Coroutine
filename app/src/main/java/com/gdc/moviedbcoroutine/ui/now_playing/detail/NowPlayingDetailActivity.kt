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
    private var isFavorite = false

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

        initListener()
    }

    private fun initListener() {
        iv_fav.setOnClickListener {
            isFavorite = if (!isFavorite) {
                iv_fav.setBackgroundResource(R.drawable.ic_favorite_black_24dp)
                Utility.showMessage(this, "Add to Favorite")
                true
            } else {
                iv_fav.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp)
                Utility.showMessage(this, "Remove from Favorite")
                false
            }
        }
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
        if (!detail.genres.isNullOrEmpty()) {
            tv_genres.text = detail.genres[0].name
        } else {
            tv_genres.text = " - "
        }
        tv_overview.text = detail.overview
        if (!detail.productionCompanies.isNullOrEmpty()) {
            img_poster_belongs.loadImage(BuildConfig.IMG_URL + "w500" + detail.productionCompanies[0].logoPath)
        }
        tv_title_belongs.text = detail.belongsToCollection
        tv_budget.text = detail.budget.toString()
        tv_revenue.text = detail.revenue.toString()
        if (!detail.productionCompanies.isNullOrEmpty()) {
            tv_companies.text = detail.productionCompanies[0].name
        } else {
            tv_companies.text = " - "
        }
        if (!detail.productionCountries.isNullOrEmpty()) {
            tv_countries.text = detail.productionCountries[0].name
        } else {
            tv_countries.text = " - "
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
