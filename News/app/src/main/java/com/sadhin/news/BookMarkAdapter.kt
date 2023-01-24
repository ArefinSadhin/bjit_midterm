package com.sadhin.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sadhin.news.model.BookMark
import com.sadhin.news.viewmodel.BookMarkViewModel
import com.sadhin.news.viewmodel.NewsViewModel


class BookMarkAdapter(private val context: Context, private val viewModel: BookMarkViewModel)
    :RecyclerView.Adapter<BookMarkAdapter.ItemViewHolder>() {
    private var listOfUsers= emptyList<BookMark>()

    class ItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image)
        val description: TextView = view.findViewById(R.id.description)
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(context).inflate(R.layout.news, parent,false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pos=listOfUsers[position]
        holder.title.text = pos.title
        holder.description.text = pos.description
        holder.date.text = pos.publishedAt

        Glide.with(holder.itemView.context)
            .load(pos.urlToImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    fun setData(users:List<BookMark>){
        listOfUsers=users
        notifyDataSetChanged()
    }
}