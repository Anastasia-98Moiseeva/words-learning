package com.example.words_learning.fragments.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.words_learning.R
import com.example.words_learning.Router
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_search_word.view.*
import kotlinx.android.synthetic.main.list_item.*
import okhttp3.*

class   SearchWord : Fragment() {

    private lateinit var router : Router

    /*val name = "Search"*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_search_word, container, false)

        layout.imageButton2.setOnClickListener {
            Thread(BackgroundFetcher()).start()
        }

        /*val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)*/

        return layout
    }

    private fun fetchTranslation(word : String, language : String) : String {
        val resource = "https://translate.yandex.net/api/v1.5/tr.json/translate"
        val apiKey = "trnsl.1.1.20190501T234428Z.90b60f7583e9d7b6.ad52ed8f1fb7aa585056ed65c1f9ba9552bc4fd5"
        val url : String = resource + "?key=" + apiKey + "&text=" + word + "&lang=" + language + "&format=plain"

        val client = OkHttpClient()
        val requestBuild = Request.Builder()
        val requestUrl = requestBuild.url(url) ?: return "Сервер не доступен"
        val request = requestUrl.build() ?: return "Сервер не доступен"
        val response1 = client.newCall(request) ?: return "Сервер не доступен"
        val response = response1.execute() ?: return "Сервер не доступен"
        return response.body()!!.string()
    }

    inner class BackgroundFetcher : Runnable {
        override fun run() {
            val textView = activity!!.findViewById<TextView>(R.id.editText)

            val translation = fetchTranslation(textView.text.toString(), "en-ru")

            activity!!.runOnUiThread(Runnable {

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(translation, HomeFeed::class.java)

                if (homeFeed != null) {
                    val textHomeFeed1 = homeFeed.text
                    if (textHomeFeed1 != null) {
                        val textHomeFeed = textHomeFeed1.toString()
                        val text = textHomeFeed.substring(1, homeFeed.text.toString().length - 1)
                        val listView = activity!!.findViewById<TextView>(R.id.textView)
                        listView.setText(text)
                    }
                }
            })

        }
    }

    override fun onResume() {
        super.onResume()
        /*val listView = activity!!.findViewById<TextView>(R.id.textView2)
        listView.setText(name)*/
    }

}


class HomeFeed(val text: ArrayList<String>)

