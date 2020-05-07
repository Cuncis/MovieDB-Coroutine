package com.gdc.moviedbcoroutine.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.gdc.moviedbcoroutine.R
import com.gdc.moviedbcoroutine.data.model.FavoriteModel
import com.gdc.moviedbcoroutine.util.Utility
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(private val context: Context): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var favoriteList = ArrayList<FavoriteModel>()
    private lateinit var removeClickListener: RemoveClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false))
    }

    override fun getItemCount(): Int{
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }

    fun setFavoriteList(list: List<FavoriteModel>) {
        favoriteList.clear()
        favoriteList.addAll(list)
        notifyDataSetChanged()
    }

    fun removeFavorite(position: Int) {
        favoriteList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun setRemoveClickListener(removeClickListener: RemoveClickListener) {
        this.removeClickListener = removeClickListener
    }

    inner class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val title: TextView = view.tv_title
        private val remove: ImageButton = view.btn_remove

        fun bind(favoriteModel: FavoriteModel) {
            title.text = favoriteModel.title
            remove.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            removeClickListener.onRemoveClick(adapterPosition)
        }
    }

    interface RemoveClickListener {
        fun onRemoveClick(position: Int)
    }

}