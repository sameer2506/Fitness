package com.example.brewapps.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.brewapps.R
import com.example.brewapps.data.entities.nowPlaying.Result
import com.example.brewapps.databinding.MovieListItemBinding
import com.squareup.picasso.Picasso

class MovieListItem(
    val arrayList: List<Result>,
    private val listener: OnItemClickListener,
    val context: Context
) : RecyclerView.Adapter<MovieListItem.MovieListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListVH = MovieListVH.from(parent)

    override fun onBindViewHolder(holder: MovieListVH, position: Int) {
        holder.bind(arrayList[position], listener)
    }

    override fun getItemCount(): Int {
        if (arrayList.isEmpty()){
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    class MovieListVH(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            list: Result,
            listener : OnItemClickListener
        )
        {
            binding.movie = list
            binding.movieListItemClick = listener
            Picasso.get().load("https://image.tmdb.org/t/p/w342${list.poster_path}").into(binding.moviePosterImage)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : MovieListVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding : MovieListItemBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.movie_list_item, parent, false)
                return MovieListVH(binding)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(list: Result)
    }
}