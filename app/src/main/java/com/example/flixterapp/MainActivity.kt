package com.example.flixterapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flixterapp.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private val SEARCH_API_KEY = BuildConfig.API_KEY
private val MOVIES_SEARCH_URL =
    "https://api.themoviedb.org/3/movie/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movies>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        moviesRecyclerView = findViewById(R.id.movies)
        val articleAdapter = MoviesAdapter(this, movies)
        moviesRecyclerView.adapter = articleAdapter

        moviesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            moviesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val client = AsyncHttpClient()
        client.get(MOVIES_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {

                    // TODO: Create the parsedJSON
                    val parsedJson = createJson().decodeFromString(
                        BaseResponse.serializer(),
                        json.jsonObject.toString()
                    )

                    // TODO: Do something with the returned json (contains article information)

                    // TODO: Save the articles and reload the screen
                    parsedJson.results?.let { list ->
                        movies.addAll(list)
                    }
                    // Reload the screen
                    articleAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}