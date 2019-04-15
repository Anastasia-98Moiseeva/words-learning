package com.example.words_learning

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.words_learning.fragments.SearchWord


class MainApplication : AppCompatActivity() {

    lateinit var router  : Router


    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                router.navigateTo(false, ::MainFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                router.navigateTo(false, ::MainFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                router.navigateTo(false, ::SearchWord)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_application)

        //  setContentView(R.layout.main_application)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        textMessage = findViewById(R.id.textView2)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        router = Router(this, R.id.fragment_container)
        if (savedInstanceState == null)
            router.navigateTo(false, ::MainFragment)
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