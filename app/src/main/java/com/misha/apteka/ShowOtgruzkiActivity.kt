package com.misha.apteka

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.database.Cursor

class ShowOtgruzkiActivity : AppCompatActivity() {
    val made_otgruz = Made_otgruz()
    fun A(){
        made_otgruz.kodOtgruzki
        made_otgruz.dataOtgruzki
        made_otgruz.kodZakaza
        made_otgruz.otgruzhenoTovara
    }
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_otgruzki) // Создайте layout для отображения данных

        val db = AppDatabase(this)
        val otgruzkiTextView = findViewById<TextView>(R.id.otgruzkiTextView) // Получите TextView

        val cursor = db.getAllOtgruzki() // Получите все отгрузки
        if (cursor.moveToFirst()) {
            var result = ""
            do {
                val kodOtgruzki = cursor.getInt(cursor.getColumnIndex("КодОтгрузки"))
                val kodZakaza = cursor.getInt(cursor.getColumnIndex("КодЗаказа"))
                val dataOtgruzki = cursor.getString(cursor.getColumnIndex("ДатаОтгрузки"))
                val otgruzhenoTovara = cursor.getInt(cursor.getColumnIndex("ОтгруженоТовара"))
                result += "Код отгрузки: $kodOtgruzki\n"
                result += "Код заказа: $kodZakaza\n"
                result += "Дата отгрузки: $dataOtgruzki\n"
                result += "Отгружено товара: $otgruzhenoTovara\n\n"
            } while (cursor.moveToNext())
            otgruzkiTextView.text = result
        } else {
            otgruzkiTextView.text = "Отгрузок нет"
        }
        cursor.close()
    }
}
