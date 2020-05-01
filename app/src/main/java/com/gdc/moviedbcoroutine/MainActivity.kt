package com.gdc.moviedbcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdc.moviedbcoroutine.ui.FavoriteFragment
import com.gdc.moviedbcoroutine.ui.now_playing.NowPlayingFragment
import com.gdc.moviedbcoroutine.ui.UpcomingFragment
import com.gdc.moviedbcoroutine.util.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.platform.ConscryptPlatform
import java.security.Security

class MainActivity : AppCompatActivity() {

    lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0f

        pager.offscreenPageLimit = 2
        tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(NowPlayingFragment(), "Now Playing")
        tabAdapter.addFragment(UpcomingFragment(), "Upcoming")
        tabAdapter.addFragment(FavoriteFragment(), "Favorite")
        pager.adapter = tabAdapter
        tab_layout.setupWithViewPager(pager)
    }
}
