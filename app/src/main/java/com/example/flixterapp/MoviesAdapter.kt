package com.example.flixterapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIES_EXTRA = "MOVIES_EXTRA"
private const val TAG = "MoviesAdapter"

class MoviesAdapter(private val context: Context, private val movies: List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() =movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.movieImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.movieTitle)
        private val voteaverageTextView = itemView.findViewById<TextView>(R.id.movieRating)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(article: Movies) {
            titleTextView.text = article.title
            voteaverageTextView.text = article.voteaverage

            Glide.with(context)
                .load(article.mediaImageUrl)
                .into(mediaImageView)
        }

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val article = movies[absoluteAdapterPosition]

            // TODO: Navigate to Details screen and pass selected article
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIES_EXTRA, article)
            context.startActivity(intent)
        }
    }
}