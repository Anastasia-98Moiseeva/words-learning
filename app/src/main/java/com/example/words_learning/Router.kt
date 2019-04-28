package com.example.words_learning

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

import java.lang.ref.WeakReference

class Router(activity : FragmentActivity, container: Int) {
    private var num_stack = 0
    private val weakActivity = WeakReference(activity)
    private val fragmentContainer = container

    fun navigateTo(addToBack : Boolean = true, fragmentFactory: () -> Fragment, clearStack : Boolean = false) {
        val activity = weakActivity.get()

        activity?.run {
            if (clearStack) {
                while (num_stack > 0) {
                    supportFragmentManager.popBackStack()
                    num_stack--
                }
            } else {
                num_stack ++
            }
            val fragment = fragmentFactory()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(fragmentContainer, fragment)
            if (addToBack) transaction.addToBackStack(fragment::class.java.simpleName)
            transaction.commit()
            num_stack ++
        }
    }

    fun navigateBack() : Boolean {
        val activity = weakActivity.get()

        activity?.run {
            if (supportFragmentManager.backStackEntryCount > 0 && num_stack > 0) {
                num_stack --

                supportFragmentManager.popBackStack()
                return true
            }
        }

        return false
    }
}