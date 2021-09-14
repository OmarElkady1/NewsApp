package com.example.newsapp.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.example.newsapp.Adapter.Adapter
import com.example.newsapp.model.News
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), Adapter.OnNewsClick {
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var madapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        newsRecyclerView = findViewById(R.id.recyclerView)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        fetchNews()

        madapter = Adapter(this)
        newsRecyclerView.adapter = madapter


    }

    fun fetchNews() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                val newsJsonArrray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArrray.length()) {
                    val newsjsonObject = newsJsonArrray.getJSONObject(i)
                    val news = News(
                        newsjsonObject.getString("title"),
                        newsjsonObject.getString("author"),
                        newsjsonObject.getString("url"),
                        newsjsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                madapter.updateData(newsArray)


            }, Response.ErrorListener {

            }
        )
        queue.add(jsonObjectRequest)
    }

    override fun onClick(news: News) {
        val builder =CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(news.url))
    }

}









