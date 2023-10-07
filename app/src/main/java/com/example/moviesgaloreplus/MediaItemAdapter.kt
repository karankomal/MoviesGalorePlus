package com.example.moviesgaloreplus

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MediaItemAdapter internal constructor(private val context: Context, private val mediaItemList: List<MediaItem>) :
    RecyclerView.Adapter<MediaItemAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val mediaItemTitle: TextView = itemView.findViewById(R.id.media_item_title) as TextView
        val mediaItemImg: ImageView = itemView.findViewById(R.id.img_media_item) as ImageView



        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val mediaItem = mediaItemList[absoluteAdapterPosition]
            val imageView = v!!.findViewById<ImageView>(R.id.img_media_item)
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("MEDIAITEM_EXTRA", mediaItem)
            val options : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as MainActivity, imageView, "poster")
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.media_item, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(childViewHolder: ChildViewHolder, position: Int) {
        val mediaItem: MediaItem = mediaItemList[position]
        childViewHolder.mediaItemTitle.text = mediaItem.title

        val moviePosterUrl = "https://image.tmdb.org/t/p/w500" + mediaItem.poster_path

        Glide.with(context)
            .load(moviePosterUrl)
            .centerCrop()
            .transform(RoundedCornersTransformation(30,10))
            .into(childViewHolder.mediaItemImg)
    }

    override fun getItemCount(): Int {
        return mediaItemList.size
    }
}