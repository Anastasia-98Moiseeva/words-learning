package com.example.words_learning.database.dictionary

import android.content.ContentValues
import android.content.Context
import com.example.words_learning.database.DBHelper

class Dictionary(context : Context) {

    private val dbHelper = DBHelper(context)
    private var wordsTranslation = ArrayList<Words>()

    fun fillWordsTranslation() {
        val words = getAllElements()
        if (words != null) {
            wordsTranslation = words
        }
    }

    fun sizeWordsTranslation() : Int{
        return wordsTranslation.size
    }

//    fun deleteElementWords(word : String) {
//        wordsTranslation.remove()
//    }

    fun removeElementPos(pos : Int) {
        remove(wordsTranslation[pos])
        wordsTranslation.removeAt(pos)
        //dictionary.remove(words[pos])
    }

    fun returnElement(word : Words) {
        wordsTranslation.add(word)
        addValue(word)
    }

    fun getElementPos(pos : Int) : Words?{
        return if (wordsTranslation.size > pos) {
            wordsTranslation[pos]
        } else {
            null
        }
    }

    fun getAllElements(): ArrayList<Words>? {
        val words = ArrayList<Words>()
        val data = dbHelper.getElements(DBHelper.TABLE_DICTIONARY)
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
        dbHelper.removeElementID(DBHelper.TABLE_DICTIONARY, word.id)
    }

    fun findWord(word : Words) : Boolean {
        return dbHelper.isElement(DBHelper.TABLE_DICTIONARY, DBHelper.COLUMN_DICTIONARY_WORD, word.word)
    }

    fun removeByWord(word: Words) {
        dbHelper.removeElementWord(DBHelper.TABLE_DICTIONARY, word.word)
    }

    fun addValue(words: Words) {
        val values = ContentValues()
        values.put(DBHelper.COLUMN_DICTIONARY_WORD, words.word)
        values.put(DBHelper.COLUMN_DICTIONARY_TRANSLATION, words.traslation)

        dbHelper.addElement(DBHelper.TABLE_DICTIONARY, values)
    }


}