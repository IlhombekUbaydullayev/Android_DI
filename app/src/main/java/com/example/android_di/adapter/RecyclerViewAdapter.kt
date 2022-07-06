package com.example.android_di.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_di.databinding.ItemLayoutPostBinding
import com.example.android_di.model.Post


class RecyclerViewAdapter(private val clickListener:(Post)->Unit,private val updateListener:(Post)->Unit): RecyclerView.Adapter<MainViewHolder>() {
    var movies = mutableListOf<Post>()
    fun setMovieList(movies: List<Post>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutPostBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.userId.text = movie.id.toString()
        holder.binding.userBody.text = movie.body
        holder.binding.userTitle.text = movie.tittle
        holder.binding.llPosts.setOnLongClickListener {
            clickListener(movie)
            true
        }

        holder.binding.llPosts.setOnClickListener {
            updateListener(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
class MainViewHolder(val binding: ItemLayoutPostBinding) : RecyclerView.ViewHolder(binding.root) {
}