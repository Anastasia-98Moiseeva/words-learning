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
        val currentDate = getCurrentDate()

        values.put(DBHelper.COLUMN_STATISTIC_WORD, word)
        values.put(DBHelper.COLUMN_STATISTIC_TIME, currentDate)
        dbHelper.addElement(DBHelper.TABLE_STATISTIC, values)
    }

    private fun getCurrentDate(): String? {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, (-1 * 7))
        return this.sdf.format(cal.time)
    }

    fun getAllElements(): ArrayList<Information>? {
        val information = ArrayList<Information>()
        val data = dbHelper.getElements(DBHelper.TABLE_STATISTIC)
        return if (data != null) {
            for (array in data) {
                information.add(Information(array[0].toInt(), array[1], array[2]))
            }
            information
        } else {
            null
        }
    }


    fun getNumWordsDate(numDays : Int) : Int {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, (-1 * numDays))
        val sdf = sdf.format(cal.time)

        return dbHelper.countWhereLessEqual(DBHelper.TABLE_STATISTIC, DBHelper.COLUMN_STATISTIC_TIME, sdf)
    }

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd/M/yyyy")

}