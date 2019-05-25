package com.example.words_learning.database.statistic

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.words_learning.database.DBHelper
import com.example.words_learning.database.DBHelper.Companion.COLUMN_STATISTIC_TIME
import com.example.words_learning.database.DBHelper.Companion.COLUMN_STATISTIC_WORD
import com.example.words_learning.database.DBHelper.Companion.TABLE_STATISTIC
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Statistic(context : Context) {

    private val dbHelper = DBHelper(context)



    fun removeWord(word: String) {
        return dbHelper.removeElementsByName(TABLE_STATISTIC, COLUMN_STATISTIC_WORD, word)
    }

    @SuppressLint("SimpleDateFormat")
    fun addValue(word: String) {
        val values = ContentValues()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        values.put(COLUMN_STATISTIC_WORD, word)
        values.put(COLUMN_STATISTIC_TIME, currentDate)
        dbHelper.addElement(TABLE_STATISTIC, values)
    }

    fun getAllElements(): ArrayList<Information>? {
        val words = ArrayList<Information>()
        val data = dbHelper.getElements(TABLE_STATISTIC)
        return if (data != null) {
            for (array in data) {
                words.add(Information(array[0].toInt(), array[1], array[2]))
            }
            words
        } else {
            null
        }
    }



    //fun


}