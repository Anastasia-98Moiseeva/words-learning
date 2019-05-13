package com.example.words_learning.database


import android.content.Context
import android.widget.BaseAdapter

class Model(context : Context, val roomModel : Boolean = false) {

    private val dbHelper = DBHelper(context)
    private val room = ValuesDatabase.getAppDataBase(context)


    fun size(): Long {
        return dbHelper.count
    }

    fun select(): ArrayList<Words>? {
        return dbHelper.getDictionary()
    }

    fun remove(word : Words) {
        dbHelper.removeElement(word)
    }


    fun addValue(words: Words) {
        dbHelper.addElement(words)
    }

}