package com.sadhin.news

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sadhin.news.model.NewsArticle
import com.sadhin.news.viewmodel.NewsViewModel

class NewsAdapter(
    private val context: Context,
     private val viewModel: NewsViewModel
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {
    private var listOfArticles= emptyList<NewsArticle>()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val image: ImageView = view.findViewById(R.id.image)
        val description: TextView = view.findViewById(R.id.description)
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(context).inflate(R.layout.news,parent,false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pos=listOfArticles[position]
        holder.title.text = pos.title
        holder.description.text = pos.description
        holder.date.text = pos.publishedAt

        Glide.with(holder.itemView.context)
            .load(pos.urlToImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)

        holder.itemView.setOnLongClickListener {
            // Toast.makeText(holder.itemView.context, "News Added to bookmarks", Toast.LENGTH_SHORT).show()
            viewModel.addBookMark(pos)
            Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return listOfArticles.size
    }
    fun setData(news:List<NewsArticle>){
        Log.d("TAG:viewmodel", "setData: ")
        listOfArticles=news
        notifyDataSetChanged()
    }
}