package com.misha.carshop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.misha.carshop.db.myfirstdb

class DB_HELPER(contex: Context) : SQLiteOpenHelper(contex, myfirstdb.DATABASE_NAME,
    null, myfirstdb.DATABASE_VERTION){
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented") //можно удалить
        db?.execSQL(myfirstdb.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented") //можно удалить
        db?.execSQL(myfirstdb.SQL_DELETE_TABLE)
        onCreate(db)
    }

}