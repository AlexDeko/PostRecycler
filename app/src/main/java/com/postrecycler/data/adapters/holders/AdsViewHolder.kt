package com.postrecycler.data.adapters.holders

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.postrecycler.R
import com.postrecycler.data.Post
import com.postrecycler.data.adapters.PostRecyclerAdapter
import kotlinx.android.synthetic.main.list_ads_item.view.*

class AdsViewHolder(adapter: PostRecyclerAdapter, view: View) : BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            imageButtonLike.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    item.like.isLiked = !item.like.isLiked

                    if (item.like.isLiked) {
                        imageButtonLike.setImageResource(R.drawable.ic_favorite_24dp)
                        item.like.count += 1L
                    } else {
                        imageButtonLike.setImageResource(R.drawable.ic_favorite_border_24dp)
                        item.like.count -= 1L
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            imageButtonReply.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT, """
                                ${item.author} (${item.date})
    
                                ${item.text}
                            """.trimIndent()
                        )
                        type = "text/plain"
                    }
                    itemView.context.startActivity(intent)
                }
            }
            adsImageButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]
                    val intent = Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(item.adsUrl)
                    }
                    itemView.context.startActivity(intent)
                }
            }

            clearAds.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    adapter.list.removeAt(adapterPosition)
                    adapter.notifyItemRemoved(adapterPosition)
                }
            }
        }
    }

    override fun bind(post: Post) {
        with(itemView) {
            this.textItem.text = post.text
            this.adsAuthor.text = post.author

            countLikes.text = post.like.count.toString()
            countReply.text = post.reply?.count.toString()
            countComments.text = post.comment.count.toString()

            imageButtonLike.setImageResource(if (post.like.isLiked) R.drawable.ic_favorite_24dp else R.drawable.ic_favorite_border_24dp)

            if (countLikes.text == "0") {
                countLikes.visibility = View.INVISIBLE
            } else countLikes.visibility = View.VISIBLE

            if (countComments.text == "0") {
                countComments.visibility = View.INVISIBLE
            } else countComments.visibility = View.VISIBLE

            if (countReply.text == "0") {
                countReply.visibility = View.INVISIBLE
            } else countReply.visibility = View.VISIBLE
        }
    }
}