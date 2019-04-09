package com.example.words_learning

import android.app.Application
import android.os.Bundle
import com.example.words_learning.fragments.MainFragment

class MainApplication : Application() {
    //lateinit var router: Router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

        companion object {
            lateinit var instance: MainApplication
                private set
        }
}
