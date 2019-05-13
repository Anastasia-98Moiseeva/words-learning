package com.example.words_learning

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.UserDictionary
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.words_learning.database.Model
import com.example.words_learning.database.Words
import com.example.words_learning.fragments.dictionary.DictionaryFragment
import com.example.words_learning.fragments.search.SearchWord
import com.example.words_learning.fragments.search.TabFragment


class MainApplication : AppCompatActivity() {

    lateinit var router  : Router
    private lateinit var model: Model


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                router.navigateTo(false, :: MainFragment, true)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_book -> {
                router.navigateTo(false, ::DictionaryFragment, true)
                return@OnNavigationItemSelectedListener true


            }
            R.id.navigation_search -> {
                router.navigateTo(false, ::TabFragment, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_application)


        //model = Model(this)
        //model.addValue(Words("rat", "Крыса"))

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_home
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)


        router = Router(this, R.id.fragment_container)
        if (savedInstanceState == null)
            router.navigateTo(false, ::MainFragment)
    }


    override fun onBackPressed() {
        if (!router.navigateBack()) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
    }



    override fun onPause() {
        super.onPause()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {


    }



}