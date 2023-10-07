package com.example.moviesgaloreplus

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

private const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1YmZmYzI2NzcwY2U3M2FmNGMxYjE0ZjUyMmIzM2Y5YSIsInN1YiI6IjY1MTczNTMxMDcyMTY2MDEzOWM0ZWIyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6ql4Eycyo-MOg-bBw3SJr9EAarufKKDGu9CA0RBi-40"
val mediaMovies : MutableList<MediaItem> = ArrayList<MediaItem>()
val mediaTV : MutableList<MediaItem> = ArrayList<MediaItem>()
val mediaPeople : MutableList<MediaItem> = ArrayList<MediaItem>()

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ParentRecyclerViewItem = findViewById<RecyclerView>(R.id.parent_recyclerview)

        val layoutManager = LinearLayoutManager(
            this@MainActivity
        )
        val parentItemAdapter = TrendingItemAdapter(ParentItemList())

        ParentRecyclerViewItem.adapter = parentItemAdapter
        ParentRecyclerViewItem.layoutManager = layoutManager
    }

    private fun ParentItemList(): List<TrendingItem> {
        val itemList: MutableList<TrendingItem> = ArrayList<TrendingItem>()

        makeAPICallMovie("https://api.themoviedb.org/3/trending/movie/week?language=en-US")
        val item = TrendingItem(
            "Movies Trending This Week",
            mediaMovies)
        itemList.add(item)

        makeAPICallTV("https://api.themoviedb.org/3/trending/tv/week?language=en-US")
        val item1 = TrendingItem(
            "TV Shows Trending This Week",
            mediaTV)
        itemList.add(item1)

        makeAPICallPeople("https://api.themoviedb.org/3/trending/person/week?language=en-US")
        val item2 = TrendingItem(
            "People Trending This Week",
            mediaPeople)
        itemList.add(item2)
        return itemList
    }

    fun makeAPICallMovie(url:String) {
        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Method.GET, url,
            Response.Listener { response ->

                val jsonObject = JSONObject(response)
                val dataArray = jsonObject.getJSONArray("results")


                for (i in 0 until dataArray.length()) {
                    val dataObject = dataArray.getJSONObject(i)
                    val mediaItem = MediaItem(
                        dataObject.getString("title"),
                        dataObject.getString("overview"),
                        dataObject.getString("poster_path"),
                        dataObject.getString("vote_average").toFloat(),
                        dataObject.getString("backdrop_path"),
                        dataObject.getString("id").toInt(),
                        dataObject.getString("release_date")
                    )
                    mediaMovies.add(mediaItem)
                }
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

    fun makeAPICallTV(url:String) {
        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Method.GET, url,
            Response.Listener { response ->

                val jsonObject = JSONObject(response)
                val dataArray = jsonObject.getJSONArray("results")


                for (i in 0 until dataArray.length()) {
                    val dataObject = dataArray.getJSONObject(i)
                    val mediaItem = MediaItem(
                        dataObject.getString("name"),
                        dataObject.getString("overview"),
                        dataObject.getString("poster_path"),
                        dataObject.getString("vote_average").toFloat(),
                        dataObject.getString("backdrop_path"),
                        dataObject.getString("id").toInt(),
                        dataObject.getString("first_air_date")
                    )
                    mediaTV.add(mediaItem)
                }
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

    fun makeAPICallPeople(url:String) {
        val queue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Method.GET, url,
            Response.Listener { response ->
                toggleVisibility()
                val jsonObject = JSONObject(response)
                val dataArray = jsonObject.getJSONArray("results")


                for (i in 0 until dataArray.length()) {
                    val dataObject = dataArray.getJSONObject(i)
                    val mediaItem = MediaItem(
                        dataObject.getString("name"),
                        dataObject.getString("known_for_department"),
                        dataObject.getString("profile_path"),
                        dataObject.getString("popularity").toFloat(),
                        dataObject.getString("profile_path"),
                        dataObject.getString("id").toInt(),
                        dataObject.getString("original_name")
                    )
                    mediaPeople.add(mediaItem)
                }
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

    fun toggleVisibility() {
        findViewById<ProgressBar>(R.id.progress).visibility = View.INVISIBLE
        findViewById<RecyclerView>(R.id.parent_recyclerview).visibility = View.VISIBLE
    }

}