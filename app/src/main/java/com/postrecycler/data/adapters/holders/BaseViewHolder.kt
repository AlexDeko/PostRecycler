package com.postrecycler.data.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.postrecycler.data.Post
import com.postrecycler.data.adapters.PostRecyclerAdapter

abstract class BaseViewHolder(val adapter: PostRecyclerAdapter, view: View) :
    RecyclerView.ViewHolder(view) {
    abstract fun bind(post: Post)
}