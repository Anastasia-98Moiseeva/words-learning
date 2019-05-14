package com.example.words_learning.database.dictionarybase


import android.content.Context
import com.example.words_learning.database.dictionarybase.DBHelper
import com.example.words_learning.database.dictionarybase.Words

class Model(context : Context, val roomModel : Boolean = false) {

    private val dbHelper = DBHelper(context)

    fun size(): Long {
        return dbHelper.count
    }

    fun select(): ArrayList<Words>? {
        return dbHelper.getDictionary()
    }

    fun remove(word : Words) {
        dbHelper.removeElementID(word)
    }

    fun findWord(word : Words) : Boolean {
        return dbHelper.findWord(word)
    }

    fun removeByWord(word: Words) {
        dbHelper.removeElementWord(word)
    }

    fun addValue(words: Words) {
        dbHelper.addElement(words)
    }


}