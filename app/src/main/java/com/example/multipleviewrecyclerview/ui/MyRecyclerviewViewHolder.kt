package com.example.multipleviewrecyclerview.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.multipleviewrecyclerview.databinding.MyItemsLayoutBinding
import com.example.multipleviewrecyclerview.databinding.TitleLayoutBinding
import com.example.multipleviewrecyclerview.databinding.UserLayoutBinding
import com.example.multipleviewrecyclerview.util.setImage

sealed class MyRecyclerviewViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    var itemClickListener : ( (view: View, item: RecyclerViewItem, position: Int) -> Unit )? = null


    class TitleViewHolder(private val binding: TitleLayoutBinding): MyRecyclerviewViewHolder(binding){
        fun bind(title: RecyclerViewItem.Title){
            binding.recommended.text = title.title
            binding.viewAll.setOnClickListener {
                itemClickListener?.invoke(it, title, adapterPosition)
            }

        }
    }

    class UserViewHolder(private val binding: UserLayoutBinding): MyRecyclerviewViewHolder(binding) {

        fun bind(user: RecyclerViewItem.User) {
            binding.apply {
                tvPostId.text = user.postId.toString()
                tvId.text = user.id.toString()
                tvName.text = user.name
                tvEmail.text = user.email
                tvBody.text = user.body
            }
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, user, adapterPosition)
            }
        }
    }




    class PostViewHolder(private  val binding: MyItemsLayoutBinding): MyRecyclerviewViewHolder(binding){
        fun bind(post: RecyclerViewItem.Post){
            binding.apply {
                tvAlbumId.text = post.albumId.toString()
                tvId.text = post.id.toString()
                tvTitle.text = post.title
                setImage(post.url, binding.image)
            }
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, post, adapterPosition)
            }
        }

    }

}