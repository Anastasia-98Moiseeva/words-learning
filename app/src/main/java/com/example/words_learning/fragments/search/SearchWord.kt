package com.example.words_learning.fragments.search

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.beust.klaxon.Klaxon
import com.example.words_learning.R
import com.example.words_learning.Router
import com.example.words_learning.enterWord
import com.example.words_learning.searchWord
import kotlinx.android.synthetic.main.fragment_dictionary.*
import kotlinx.android.synthetic.main.fragment_search_word.view.*
import kotlinx.android.synthetic.main.list_item.*
import okhttp3.*
import org.w3c.dom.Text
import java.io.IOException
import java.util.concurrent.TimeUnit


class SearchWord() : Fragment() {

    private lateinit var router : Router
    private lateinit var textView: TextView

    private lateinit var myActivity : FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity(), R.id.fragment_container)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater.inflate(R.layout.fragment_search_word, container, false)

        layout.imageButton2.setOnClickListener {
            Thread(BackgroundFetcher()).start()
        }

        return layout
    }

    fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

    }

    private fun fetchTranslation(word : String, language : String) : String {

        try {
            val resource = "https://translate.yandex.net/api/v1.5/tr.json/translate"
            val apiKey = "trnsl.1.1.20190501T234428Z.90b60f7583e9d7b6.ad52ed8f1fb7aa585056ed65c1f9ba9552bc4fd5"
            val url: String = "$resource?key=$apiKey&text=$word&lang=$language&format=plain"

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .build()

            val response = createClient().newCall(request).execute()
            if(response.isSuccessful){
                return response.body()!!.string()
            }
        }
        catch (e: IOException){
            return "404"
        }
        return ""
    }

    inner class BackgroundFetcher : Runnable {
        override fun run() {
            /*Thread.sleep(5000)*/
            activity?.let {
                val translation = fetchTranslation(it.findViewById<TextView>(R.id.editText).text.toString(), "en-ru")

                it.runOnUiThread(Runnable {

                    val klaxon = Klaxon()
                    val homeFeed = klaxon.parse<HomeFeed>(translation)

                    if (homeFeed != null) {
                        val textHomeFeed1 = homeFeed.text
                        val textHomeFeed = textHomeFeed1.toString()
                        val text = textHomeFeed.substring(1, homeFeed.text.toString().length - 1)
                        val hm = it
                        if (hm != null) {
                            hm.findViewById<TextView>(R.id.textView).text = text
                        }
                    }
                })
            }

        }
    }

    override fun onResume() {
        super.onResume()
        myActivity = activity!!
        textView = activity!!.findViewById(R.id.textView)
        if (enterWord != "") {
            myActivity.findViewById<TextView>(R.id.editText).text = enterWord
            myActivity.findViewById<TextView>(R.id.textView).text = searchWord
            if (searchWord != "") {
                textView.text = searchWord
            }
        } else {
            enterWord = activity!!.findViewById<TextView>(R.id.editText).text.toString()
            searchWord = activity!!.findViewById<TextView>(R.id.textView).text.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        enterWord = activity!!.findViewById<TextView>(R.id.editText).text.toString()
        searchWord = activity!!.findViewById<TextView>(R.id.textView).text.toString()
    }
}


class HomeFeed(val code : Int, val lang : String, val text: ArrayList<String>)

