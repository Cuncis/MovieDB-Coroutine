package com.gdc.moviedbcoroutine.ui.now_playing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdc.moviedbcoroutine.BuildConfig
import com.gdc.moviedbcoroutine.R
import com.gdc.moviedbcoroutine.data.model.NowPlaying
import com.gdc.moviedbcoroutine.util.Utility.Companion.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class NowPlayingAdapter(var list: ArrayList<NowPlaying>): RecyclerView.Adapter<NowPlayingAdapter.MainViewHolder>() {

    private lateinit var detailClickListener: DetailClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setNowPlayingList(list: List<NowPlaying>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setDetailClickListener(detailClickListener: DetailClickListener) {
        this.detailClickListener = detailClickListener
    }

    inner class MainViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val ivPoster: ImageView = view.img_poster
        private val tvTitle: TextView = view.tv_title
        private val tvOverview: TextView = view.tv_overview
        private val tvReleaseDate: TextView = view.tv_release_date
        private val btnDetail: Button = view.btn_detail

        fun bind(nowPlaying: NowPlaying) {
            ivPoster.loadImage(BuildConfig.IMG_URL + "w154" +  nowPlaying.posterPath)
            tvTitle.text = nowPlaying.title
            tvOverview.text = nowPlaying.overview
            tvReleaseDate.text = nowPlaying.releaseDate
            btnDetail.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            detailClickListener.onDetailClick(adapterPosition)
        }
    }

    interface DetailClickListener {
        fun onDetailClick(position: Int)
    }

}