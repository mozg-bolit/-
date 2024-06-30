package com.misha.apteka

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class AppDatabase(context: Context) : SQLiteOpenHelper(context, "Mydb", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Создание таблиц
        db.execSQL("CREATE TABLE Товары (" +
                "КодТовара INTEGER PRIMARY KEY AUTOINCREMENT," +
                "НаименованиеТовара VARCHAR(255)," +
                "ЦенаТовара DOUBLE" +
                ");")

        db.execSQL("CREATE TABLE Заказы (" +
                "КодЗаказа INTEGER PRIMARY KEY AUTOINCREMENT," +
                "НаименованиеЗаказчика VARCHAR(255)," +
                "АдресЗаказчика VARCHAR(255)," +
                "Телефон VARCHAR(20)," +
                "НомерДоговора INTEGER," +
                "ДатаЗаключениеДоговора DATE," +
                "КодТовара INTEGER," +
                "ПлановаяПоставка INTEGER," +
                "FOREIGN KEY (КодТовара) REFERENCES Товары(КодТовара)" +
                ");")

        db.execSQL("CREATE TABLE Отгрузки (" +
                "КодОтгрузки INTEGER PRIMARY KEY AUTOINCREMENT," +
                "КодЗаказа INTEGER," +
                "ДатаОтгрузки DATE," +
                "ОтгруженоТовара INTEGER," +
                "FOREIGN KEY (КодЗаказа) REFERENCES Заказы(КодЗаказа)" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Логика обновления схемы базы данных
    }

    fun getTovarByName(name: String): Cursor? {
        val db = this.readableDatabase
        return db.query("Товары", null, "НаименованиеТовара = ?", arrayOf(name), null, null, null)
    }



    fun addZakaz(
        naimZakaz: String,
        adresZakaz: String,
        telefon: String,
        nomerDogovora: String,
        dataZaklDogovora: String,
        kodTovara: String,
        planPostavka: String,
        context: Context
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("НаименованиеЗаказчика", naimZakaz)
        values.put("АдресЗаказчика", adresZakaz)
        values.put("Телефон", telefon)
        values.put("НомерДоговора", nomerDogovora)
        values.put("ДатаЗаключениеДоговора", dataZaklDogovora)
        values.put("КодТовара", kodTovara)
        values.put("ПлановаяПоставка", planPostavka)
        val success = db.insert("Заказы", null, values)
        if (success != -1L) {            Toast.makeText(context, "Заказ добавлен", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Ошибка добавления заказа", Toast.LENGTH_SHORT).show()
        }
    }

    fun addTovar(naimTovara: String, cenaTovara: String, kodT:String, context: Tovar) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("НаименованиеТовара", naimTovara)
        values.put("ЦенаТовара", cenaTovara)
        values.put("КодТовара", kodT)
        val success = db.insert("Товары", null, values)
    }

    fun addOtgruzka(kodZakaza: String, kodOtgruzki: String, dataOtgruzki: String, otgruzhenoTovara: String, context: Context) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("КодОтгрузки",kodOtgruzki)
        values.put("КодЗаказа", kodZakaza)
        values.put("ДатаОтгрузки", dataOtgruzki)
        values.put("ОтгруженоТовара", otgruzhenoTovara)
        val success = db.insert("Отгрузки", null, values)
        if (success != -1L) {
            Toast.makeText(context, "Отгрузка добавлена", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Ошибка добавления отгрузки", Toast.LENGTH_SHORT).show()
        }
    }

    // Методы для удаления данных

    fun deleteAllZakazy() {
        val db = this.writableDatabase
        db.delete("Zakazy", null, null) // Удаление всех записей
        db.close()
    }


    fun deleteAllTovary() {
        val db = this.writableDatabase
        db.delete("Tovary", null, null) // Удаление всех записей
        db.close()
    }

    fun deleteAllOtgruzki() {
        val db = this.writableDatabase
        db.delete("Otgruzki", null, null) // Удаление всех записей
        db.close()
    }

    // Методы для получения данных (необязательные, но полезные)

    fun getAllTovary(): Cursor {
        val db = this.readableDatabase
        return db.query("Товары", null, null, null, null, null, null)
    }

    fun getAllZakazy(): Cursor {
        val db = this.readableDatabase
        return db.query("Заказы", null, null, null, null, null, null)
    }

    fun getAllOtgruzki(): Cursor {
        val db = this.readableDatabase
        return db.query("Отгрузки", null, null, null, null, null, null)
    }


}

