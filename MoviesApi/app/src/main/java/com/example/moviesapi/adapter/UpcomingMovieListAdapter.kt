package com.example.moviesapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapi.R
import com.example.moviesapi.model.ResultUpcomingMovies

class UpcomingMovieListAdapter (
    private val upcomingMoviewList: List<ResultUpcomingMovies>,
    private val context: Context
) : RecyclerView.Adapter<UpcomingMovieListAdapter.ViewHolder>() {


    private lateinit var mListner : onItemClicklistener
    
    class ViewHolder(itemView: View ,click_Listener: onItemClicklistener) : RecyclerView.ViewHolder(itemView) {

        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieName: TextView = itemView.findViewById(R.id.movie_name)
        val adult: TextView = itemView.findViewById(R.id.adult)
        val date: TextView = itemView.findViewById(R.id.date)
        val bookNow: TextView = itemView.findViewById(R.id.book_now)
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.relative_layout)

        init {
            relativeLayout.setOnClickListener {
                click_Listener.onclick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row_upcoming_movie, parent, false)
        return ViewHolder(view,mListner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = upcomingMoviewList[position]


        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/"+currentItem.poster_path)
            .override(300, 200)
            .into(holder.movieImage);

        holder.movieName.setText(currentItem.original_title)
        holder.date.setText(currentItem.release_date)
        if (currentItem.adult==true)
        holder.adult.setText("adult")
        else
            holder.adult.setText("Non adult")

    }

    override fun getItemCount(): Int {
        return upcomingMoviewList.size
    }

    interface onItemClicklistener{
        fun onclick(position: Int)
    }
    fun setOnitemClickListener(listener : onItemClicklistener)
    {
        mListner = listener
    }
}