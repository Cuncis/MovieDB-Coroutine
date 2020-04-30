package com.gdc.moviedbcoroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.util.Utility.Companion.loadImage
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_now_playing.view.*

class MainAdapter(var list: ArrayList<NowPlaying>): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setNowPlayingList(list: List<NowPlaying>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivPoster: ImageView = view.img_poster
        val tvTitle: TextView = view.tv_title
        val tvOverview: TextView = view.tv_overview
        val tvReleaseDate: TextView = view.tv_release_date

        fun bind(nowPlaying: NowPlaying) {
            ivPoster.loadImage(BuildConfig.IMG_URL + "w154" +  nowPlaying.posterPath)
            tvTitle.text = nowPlaying.title
            tvOverview.text = nowPlaying.overview
            tvReleaseDate.text = nowPlaying.releaseDate
        }
    }

}