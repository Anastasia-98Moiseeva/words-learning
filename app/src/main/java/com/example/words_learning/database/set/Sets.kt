package com.example.words_learning.database.set

import android.content.ContentValues
import android.content.Context
import com.example.words_learning.database.DBHelper
import com.example.words_learning.database.DBHelper.Companion.TABLE_SETS


class Sets(context : Context) {

    private val dbHelper = DBHelper(context)

    fun getAllElements(): ArrayList<Set>? {
        val sets = ArrayList<Set>()
        val data = dbHelper.getDictionary()
        return if (data != null) {
            for (array in data) {
                sets.add(Set(array[0].toInt(), array[1], array[2], array[3]))
            }
            sets
        } else {
            null
        }
    }

    fun remove(set : Set) {
        dbHelper.removeElementID(TABLE_SETS, set.id)
    }

    fun findSet(set : Set) : Boolean {
        return dbHelper.findWord(TABLE_SETS, set.set)
    }

    fun removeByWord(word: Set) {
        dbHelper.removeElementWord(TABLE_SETS, word.word)
    }

    fun addValue(set: Set) {
        val values = ContentValues()
        values.put(DBHelper.COLUMN_SET, set.set)
        values.put(DBHelper.COLUMN_WORD, set.word)
        values.put(DBHelper.COLUMN_TRANSLATION, set.traslation)

        dbHelper.addElement(TABLE_SETS, values)
    }


}