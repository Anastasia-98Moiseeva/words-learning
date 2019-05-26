package com.example.words_learning.database.statistic

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.words_learning.database.DBHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList



class Statistic(context : Context) {

    private val dbHelper = DBHelper(context)



    fun removeWord(word: String) {
        return dbHelper.removeElementsByName(DBHelper.TABLE_STATISTIC, DBHelper.COLUMN_STATISTIC_WORD, word)
    }

    private fun findWord (word : String) : Boolean{
        return dbHelper.findWord(DBHelper.TABLE_STATISTIC, DBHelper.COLUMN_STATISTIC_WORD, word)
    }

    @SuppressLint("SimpleDateFormat")
    fun addWordInformation(word: String) {
        if (findWord(word)) {
            return
        }

        val values = ContentValues()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        values.put(DBHelper.COLUMN_STATISTIC_WORD, word)
        values.put(DBHelper.COLUMN_STATISTIC_TIME, currentDate)
        dbHelper.addElement(DBHelper.TABLE_STATISTIC, values)
    }

    fun getAllElements(): ArrayList<Information>? {
        val words = ArrayList<Information>()
        val data = dbHelper.getElements(DBHelper.TABLE_STATISTIC)
        return if (data != null) {
            for (array in data) {
                words.add(Information(array[0].toInt(), array[1], array[2]))
            }
            words
        } else {
            null
        }
    }



    fun getNumWordsDate(numDays : Int) : Int {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, (-1 * numDays))
        val sdf = sdf.format(cal.time)

        return dbHelper.countWhereLessEqual(DBHelper.TABLE_STATISTIC, DBHelper.COLUMN_STATISTIC_WORD, sdf)
    }

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd/M/yyyy")

}