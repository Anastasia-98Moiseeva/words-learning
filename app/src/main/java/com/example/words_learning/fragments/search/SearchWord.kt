package com.example.words_learning.fragments.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.adapters.SearchAdapter
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

/*
import com.example.words_learning.fragments.request.RequestUrlCreator
*/

class SearchWord : Fragment() {

    private lateinit var router : Router
    val name = "Search"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_search_word, container, false)

        layout = createButtons(layout)
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)

        return layout
    }

    private fun createButtons(layout: View) : View{

        return layout
    }

    override fun onResume() {
        super.onResume()
        val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)
    }

    fun fetchJson(word : String, language : String, recycler : RecyclerView) {
        val resource : String = "https://translate.yandex.net/api/v1.5/tr.json/translate"
        val apiKey : String = "trnsl.1.1.20190501T234428Z.90b60f7583e9d7b6.ad52ed8f1fb7aa585056ed65c1f9ba9552bc4fd5"
        var url : String = resource + "?key=" + apiKey + "&text=" + word + "&lang=" + language + "&format=plain"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call?, response: Response?){
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                recycler.adapter = SearchAdapter(homeFeed)

                activity!!.runOnUiThread {
                    recycler.adapter = SearchAdapter(homeFeed)
                }
           }

            override fun onFailure(call: Call?, e: IOException?){
                println("Failed to execute request")
            }

        })
    }
}

class HomeFeed(val text: ArrayList<String>)
