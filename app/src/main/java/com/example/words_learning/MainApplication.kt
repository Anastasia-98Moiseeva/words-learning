package com.example.words_learning

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainApplication : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_application)


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