package com.example.flixterapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var releaseTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.movieImage)
        titleTextView = findViewById(R.id.movieTitle)
        overviewTextView = findViewById(R.id.movieOverview)
        ratingTextView = findViewById(R.id.movieRating)
        releaseTextView = findViewById(R.id.movieReleaseDate)
        // TODO: Get the extra from the Intent
        val movie = intent.getSerializableExtra(MOVIES_EXTRA) as Movies
        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = movie.title
        overviewTextView.text = movie.overview
        ratingTextView.text = movie.voteaverage
        releaseTextView.text = movie.rdate
        // TODO: Load the media image
        Glide.with(this)
            .load(movie.mediaImageUrl)
            .into(mediaImageView)

    }
}