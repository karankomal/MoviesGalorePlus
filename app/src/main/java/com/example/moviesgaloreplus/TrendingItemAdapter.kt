package com.example.moviesgaloreplus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.moviesgaloreplus.TrendingItemAdapter.ParentViewHolder

class TrendingItemAdapter internal constructor(private val itemList: List<TrendingItem>) : RecyclerView.Adapter<ParentViewHolder>() {
    private val viewPool = RecycledViewPool()
    private lateinit var context : Context
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ParentViewHolder {
        context = viewGroup.context
        val view = LayoutInflater.from(context).inflate(R.layout.trending_item, viewGroup, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(
        parentViewHolder: ParentViewHolder,
        position: Int
    ) {
        val parentItem = itemList[position]
        parentViewHolder.ParentItemTitle.text = parentItem.parentItemTitle

        val layoutManager = LinearLayoutManager(
            parentViewHolder.ChildRecyclerView
                .context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager.initialPrefetchItemCount = parentItem
            .childItemList
            .size
        val childItemAdapter = MediaItemAdapter(
            context,
            parentItem
                .childItemList
        )
        parentViewHolder.ChildRecyclerView.layoutManager = layoutManager
        parentViewHolder.ChildRecyclerView.adapter = childItemAdapter
        parentViewHolder.ChildRecyclerView
            .setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ParentItemTitle: TextView
        val ChildRecyclerView: RecyclerView

        init {
            ParentItemTitle = itemView
                .findViewById(
                    R.id.trending_item_title
                )
            ChildRecyclerView = itemView
                .findViewById(
                    R.id.child_recyclerview
                )
        }
    }
}