package com.example.words_learning.database.statistic

import android.content.ContentValues
import android.content.Context
import com.example.words_learning.database.DBHelper
import com.example.words_learning.database.DBHelper.Companion.COLUMN_STATISTIC_WORD
import com.example.words_learning.database.DBHelper.Companion.TABLE_STATISTIC

class Statistic(context : Context) {

    private val dbHelper = DBHelper(context)



    fun removeWord(word: String) {
        return dbHelper.removeElementsByName(TABLE_STATISTIC, COLUMN_STATISTIC_WORD, word)
    }

    fun addValue(word: Infromation) {
        val values = ContentValues()
        values.put(DBHelper.COLUMN_STATISTIC_WORD, word.word)
        values.put(DBHelper.COLUMN_STATISTIC_TIME, word.learnedTime)
        dbHelper.addElement(TABLE_STATISTIC, values)
    }

    //fun


}