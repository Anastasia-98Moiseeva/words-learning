package com.example.words_learning.database


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteCursorDriver
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQuery

class Words (id1 : Int, word1: String, translation1: String){
    var id : Int = id1
    var word: String = word1
    var traslation: String = translation1
}

class DBHelper(context: Context,
               name: String = DATABASE_NAME,
               factory: SQLiteDatabase.CursorFactory = Factory(),
               version: Int = DATABASE_VERSION) : SQLiteOpenHelper(context, name, factory, version) {

    val count: Long
        get() {
            val db = readableDatabase
            val regionQuery = "select Count(*) as count from test"
            var result : Long = 0
            db?.use {
                val cur = it.rawQuery(regionQuery, arrayOf<String>())
                cur?.use {
                    it.moveToFirst()
                    result = it.getLong(it.getColumnIndexOrThrow("count"))
                }
            }
            return result
        }

    private class Factory : SQLiteDatabase.CursorFactory {
        override fun newCursor(sqLiteDatabase: SQLiteDatabase, sqLiteCursorDriver: SQLiteCursorDriver, s: String?, sqLiteQuery: SQLiteQuery): Cursor {
            return SQLiteCursor(sqLiteCursorDriver, s, sqLiteQuery)
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_WORDS_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, j: Int) {
        //val db = writableDatabase
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY)
    }

    fun addElement(words: Words) {
        val values = ContentValues()
        values.put(COLUMN_WORD, words.word)
        values.put(COLUMN_TRANSLATION, words.traslation)

        val db = this.writableDatabase

        db.insert(TABLE_DICTIONARY, null, values)

        db.close()
    }

    fun removeElementID(word: Words) {
        val db = writableDatabase
        db.delete(TABLE_DICTIONARY, COLUMN_ID + "=?", arrayOf((word.id).toString())).toLong()
    }

    fun removeElementWord(word: Words) {
        val db = writableDatabase
        db.delete(TABLE_DICTIONARY, COLUMN_WORD + "=?", arrayOf((word.word))).toLong()

    }

    fun findWord(word: Words) : Boolean {
        val db = readableDatabase

        val selectQuery = "SELECT * FROM " + TABLE_DICTIONARY + " WHERE " + COLUMN_WORD + " = "+ "'" + word.word + "'"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.let {
                return (cursor.getCount() > 0)
            }
        return false
    }


    fun getDictionary() : ArrayList<Words>? {
        val db = readableDatabase
        var words = ArrayList<Words>()
        val selectQuery = "SELECT  * FROM " + TABLE_DICTIONARY
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.let {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                    val word = cursor.getString(cursor.getColumnIndex(COLUMN_WORD))
                    val translate = cursor.getString(cursor.getColumnIndex(COLUMN_TRANSLATION))
                    val wordTranslate = Words(id, word, translate)
                    words.add(wordTranslate)
                }

                cursor.close()
                return words
            }
            cursor.close()
        }

        return null
    }


    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "productDB.db"

        val TABLE_DICTIONARY = "dictionary"

        val COLUMN_ID = "_id"
        val COLUMN_WORD = "word"
        val COLUMN_TRANSLATION = "translation"

        val CREATE_WORDS_TABLE = ("CREATE TABLE " +
                TABLE_DICTIONARY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_WORD
                + " TEXT," + COLUMN_TRANSLATION + " TEXT" + ");")
    }

}
