package com.example.words_learning

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

import java.lang.ref.WeakReference
import android.os.Bundle
import android.R.id
import android.os.Message


class Router(activity : FragmentActivity, container: Int) {
    private var num_stack = 0
    private val weakActivity = WeakReference(activity)
    private val fragmentContainer = container
    private var id_window = 0

    fun navigateTo(addToBack : Boolean = true, fragmentNew: () -> Fragment, changeStack : Int = 0, massege1: Int = 0) {
        val activity = weakActivity.get()

        activity?.run {
            if (changeStack != 0) {
                if (id_window == 0) {
                    id_window = changeStack
                } else {
                    if (id_window == changeStack) {
                        return
                    } else {
                        id_window = changeStack
                        supportFragmentManager.popBackStack()
                        num_stack--
                    }
                }
            }

            val fragment = fragmentNew()
            if (massege1 != 0) {
                val args = Bundle()
                //args.putString(message, msg)
                args.putInt(massege, massege1)
                fragment.arguments = args
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(fragmentContainer, fragment)
            if (addToBack) transaction.addToBackStack(fragment::class.java.simpleName)
            transaction.commit()
            num_stack ++
        }
    }

    fun navifatebase() : Boolean {
        if (id_window == 0)
            return true

        val activity = weakActivity.get()
        activity?.run {
            if (((supportFragmentManager.backStackEntryCount == 0) && (id_window == 0))
                ||
                ((supportFragmentManager.backStackEntryCount == 1) && (id_window != 0))) {
                id_window = 0
                if (supportFragmentManager.backStackEntryCount == 1) {
                    supportFragmentManager.popBackStack()
                    num_stack--
                }
                navigateTo(false, ::MainFragment)
                return true
            } else {
                supportFragmentManager.popBackStack()
                id_window = 0
                num_stack--
                return true
            }
        }
        return false
    }

    fun navigateBack() : Boolean {
        val activity = weakActivity.get()

        activity?.run {
            if (id_window != 0) {
                activity.finish()
            }
            if (supportFragmentManager.backStackEntryCount > 0 && num_stack > 0) {
                num_stack --

                supportFragmentManager.popBackStack()
                return true
            }
        }

        return false
    }
}