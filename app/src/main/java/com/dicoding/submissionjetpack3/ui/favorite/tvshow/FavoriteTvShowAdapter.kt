package com.dicoding.submissionjetpack3.ui.favorite.tvshow

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dicoding.submissionjetpack3.BuildConfig.IMAGE_URL
import com.dicoding.submissionjetpack3.R
import com.dicoding.submissionjetpack3.data.source.local.entity.TvEntity
import com.dicoding.submissionjetpack3.databinding.ItemMovieBinding

class FavoriteTvShowAdapter : PagedListAdapter<TvEntity, FavoriteTvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvEntity) {
            with(binding) {
                tvTitle.text = tv.name
                tvGenre.text = tv.voteAverage.toString()

                Glide.with(itemView.context)
                        .asBitmap()
                        .load(IMAGE_URL + tv.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_movie_poster_placeholder))
                        .transform(RoundedCorners(28))
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                ivPoster.setImageBitmap(resource)

                                Palette.from(resource).generate { palette ->
                                    val defValue = itemView.resources.getColor(R.color.dark, itemView.context.theme)
                                    cardItem.setCardBackgroundColor(palette?.getDarkMutedColor(defValue)
                                            ?: defValue)
                                }
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {
                            }
                        })

                itemView.setOnClickListener { onItemClickCallback.onItemClicked(tv.id.toString()) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(id: String)
    }
}