package com.misha.apteka

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.database.Cursor

class ShowTovaryActivity : AppCompatActivity() {
    val tovar = Tovar()
    fun A(){
        tovar.kodT
        tovar.cenaT
        tovar.naimT
    }
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_tovary) // Создайте layout для отображения данных

        val db = AppDatabase(this)
        val tovaryTextView = findViewById<TextView>(R.id.tovaryTextView) // Получите TextView


        val cursor = db.getAllTovary() // Получите все товары
        if (cursor.moveToFirst()) {
            var result = ""
            do {
                val  kodT = cursor.getInt(cursor.getColumnIndex("КодТовара"))
                val naimT = cursor.getString(cursor.getColumnIndex("НаименованиеТовара"))
                val cenaT = cursor.getDouble(cursor.getColumnIndex("ЦенаТовара"))
                val kodTovaran = ""
                result += "Код: $kodT"
                result += "Название: $naimT"
                result += "Цена: $cenaT"
            } while (cursor.moveToNext())
            tovaryTextView.text = result
        } else {
            tovaryTextView.text = "Товаров нет"
        }
        cursor.close()
    }
}
