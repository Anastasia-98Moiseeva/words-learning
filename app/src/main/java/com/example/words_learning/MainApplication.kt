package com.example.words_learning

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.words_learning.database.DBHelper
import com.example.words_learning.database.dictionary.Dictionary
import com.example.words_learning.fragments.dictionary.DictionaryFragment
import com.example.words_learning.fragments.search.TabFragment


class MainApplication : AppCompatActivity() {

    lateinit var router  : Router
    private lateinit var model: Dictionary
    private val dbHelper = DBHelper(this)

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                router.navifatebase()
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_book -> {
                router.navigateTo(true, ::DictionaryFragment, 1)
                return@OnNavigationItemSelectedListener true


            }
            R.id.navigation_search -> {
                router.navigateTo(true, ::TabFragment, 2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_application)


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