package com.example.moviesgaloreplus

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

private const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1YmZmYzI2NzcwY2U3M2FmNGMxYjE0ZjUyMmIzM2Y5YSIsInN1YiI6IjY1MTczNTMxMDcyMTY2MDEzOWM0ZWIyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6ql4Eycyo-MOg-bBw3SJr9EAarufKKDGu9CA0RBi-40"

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mediaImageView: ImageView = findViewById(R.id.mediaImage)
        val titleTextView: TextView = findViewById(R.id.mediaTitle)
        val ratingBar: RatingBar = findViewById(R.id.ratingStars)
        val abstractTextView : TextView = findViewById(R.id.mediaOverview)
        val dateTextView : TextView = findViewById(R.id.mediaDate)
        val media = intent.getSerializableExtra("MEDIAITEM_EXTRA") as MediaItem

        titleTextView.text = media.title
        ratingBar.rating = media.vote_average?.div(2)!!
        abstractTextView.text = media.overview
        dateTextView.text = "Released on: " + media.date

        val mediaImageUrl = "https://image.tmdb.org/t/p/w500" + media.poster_path
        Glide.with(this)
            .load(mediaImageUrl)
            .into(mediaImageView)

        getIMDBid(media.id!!, media)

        findViewById<ImageView>(R.id.mediaImage).setOnLongClickListener {
            try {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse( "https://www.imdb.com/title/" + media.imdb_id))
                this.startActivity(browserIntent)
                Toast.makeText(this, "IMDB page for " + media.title.toString() + " is opening!", Toast.LENGTH_SHORT).show()
            }
            catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "IMDB page for " + media.title.toString() + " could not open.", Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    private fun getIMDBid(id: Int, media: MediaItem) {
        val imdbIDURL = "https://api.themoviedb.org/3/movie/$id/external_ids"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(
            Method.GET, imdbIDURL,
            Response.Listener { response ->
                val jsonObject = JSONObject(response)
                media.imdb_id = jsonObject.getString("imdb_id")
            },
            Response.ErrorListener { e ->
                Log.e("volley", "error occurred.")
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["accept"] = "application/json"
                headers["Authorization"] = "Bearer $ACCESS_TOKEN"
                return headers
            }
        }
        queue.add(stringRequest)
    }
}