package com.misha.carshop.db

import android.provider.BaseColumns

object myfirstdb: BaseColumns {
    const val TABLE_NAME = "Отчет"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "subtitle"

    const val DATABASE_NAME = "Autontificetion"
    const val DATABASE_VERTION = 1

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_CONTENT"
    const val SQL_DELETE_TABLE = "DROP TABLE IS EXITS $TABLE_NAME"
}

// id=0   title   content
// id=1   title   content