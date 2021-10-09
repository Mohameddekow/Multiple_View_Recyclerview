package com.example.multipleviewrecyclerview.ui


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multipleviewrecyclerview.R
import com.example.multipleviewrecyclerview.databinding.MyItemsLayoutBinding
import com.example.multipleviewrecyclerview.databinding.TitleLayoutBinding
import com.example.multipleviewrecyclerview.databinding.UserLayoutBinding

class MyAdapter(): RecyclerView.Adapter<MyRecyclerviewViewHolder>(){
    private lateinit var binding: MyItemsLayoutBinding

    var items = listOf<RecyclerViewItem>()

    fun setData(item: List<RecyclerViewItem>) {
        items = item
        notifyDataSetChanged()
    }



    var itemClickListener : ( (view: View, item: RecyclerViewItem, position: Int) -> Unit )? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerviewViewHolder {

       return  when(viewType){
            R.layout.title_layout -> MyRecyclerviewViewHolder.TitleViewHolder(
               TitleLayoutBinding.inflate(
                   LayoutInflater.from(parent.context),
                   parent,
                   false
               )
            )

            R.layout.user_layout-> {
                MyRecyclerviewViewHolder.UserViewHolder(
                    UserLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            }

            R.layout.my_items_layout -> MyRecyclerviewViewHolder.PostViewHolder(
                MyItemsLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalAccessException("not A valid layout")
        }

    }


    override fun onBindViewHolder(holder: MyRecyclerviewViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener

        when(holder){
            is MyRecyclerviewViewHolder.PostViewHolder -> holder.bind(items[position] as RecyclerViewItem.Post)

            is MyRecyclerviewViewHolder.TitleViewHolder -> holder.bind(items[position] as RecyclerViewItem.Title)

            is MyRecyclerviewViewHolder.UserViewHolder -> holder.bind(items[position] as RecyclerViewItem.User)

        }



    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
       return  when(items[position]){
            is RecyclerViewItem.Post -> R.layout.my_items_layout
            is RecyclerViewItem.Title -> R.layout.title_layout
            is RecyclerViewItem.User -> R.layout.user_layout
       }

    }



}