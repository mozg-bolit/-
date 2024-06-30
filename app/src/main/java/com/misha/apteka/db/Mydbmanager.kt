package com.misha.carshop.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class Mydbmanager(val contex:Context) {
    val DB_HELPER = DB_HELPER(contex)
    var db: SQLiteDatabase? = null



    fun openDb(){
        db = DB_HELPER.writableDatabase
    }

    fun insertToDb(title:String,content:String){
        val values = ContentValues().apply {
            put(myfirstdb.COLUMN_NAME_TITLE,title)
            put(myfirstdb.COLUMN_NAME_CONTENT,content)
        }
        db?.insert(myfirstdb.TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readDbData() : ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(
            myfirstdb.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )


            while(cursor?.moveToNext()!!){
                val dataText = cursor.getString(cursor.getColumnIndex(myfirstdb.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        cursor.close()

        return dataList
    }

    fun closeDb(){
        DB_HELPER.close()
    }
}