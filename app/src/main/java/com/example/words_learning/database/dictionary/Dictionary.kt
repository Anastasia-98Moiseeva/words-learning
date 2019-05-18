package com.example.words_learning.database.dictionary

import android.content.ContentValues
import android.content.Context
import com.example.words_learning.database.DBHelper
import com.example.words_learning.database.DBHelper.Companion.TABLE_DICTIONARY

class Dictionary(context : Context) {

    private val dbHelper = DBHelper(context)

    fun getAllElements(): ArrayList<Words>? {
        val words = ArrayList<Words>()
        val data = dbHelper.getDictionary()
        return if (data != null) {
            for (array in data) {
                words.add(Words(array[0].toInt(), array[1], array[2]))
            }
            words
        } else {
            null
        }
    }

    fun remove(word : Words) {
        dbHelper.removeElementID(TABLE_DICTIONARY, word.id)
    }

    fun findWord(word : Words) : Boolean {
        return dbHelper.findWord(TABLE_DICTIONARY, word.word)
    }

    fun removeByWord(word: Words) {
        dbHelper.removeElementWord(TABLE_DICTIONARY, word.word)
    }

    fun addValue(words: Words) {
        val values = ContentValues()
        values.put(DBHelper.COLUMN_WORD, words.word)
        values.put(DBHelper.COLUMN_TRANSLATION, words.traslation)

        dbHelper.addElement(TABLE_DICTIONARY, values)
    }


}