package com.misha.apteka

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.database.Cursor

class ShowZakazyActivity : AppCompatActivity() {

    val zakazy = Zakaz()
    fun A(){
        zakazy.telefon
        zakazy.kodTovara
        zakazy.nomerDogovora
        zakazy.adresZ
        zakazy.dataZaklDogovora
        zakazy.planPostavka
        zakazy.kodZakaza
        zakazy.naimZ
    }

    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_zakazy) // Создайте layout для отображения данных

        val db = AppDatabase(this)
        val zakazyTextView = findViewById<TextView>(R.id.zakazyTextView) // Получите TextView

        val cursor = db.getAllZakazy() // Получите все заказы
        if (cursor.moveToFirst()) {
            var result = ""
            do {
                val kodZakaza = cursor.getInt(cursor.getColumnIndex("КодЗаказа"))
                val naimZ = cursor.getString(cursor.getColumnIndex("НаименованиеЗаказчика"))
                val adresZ = cursor.getString(cursor.getColumnIndex("АдресЗаказчика"))
                val telefon = cursor.getString(cursor.getColumnIndex("Телефон"))
                val nomerDogovora = cursor.getInt(cursor.getColumnIndex("НомерДоговора"))
                val dataZaklDogovora = cursor.getString(cursor.getColumnIndex("ДатаЗаключениеДоговора"))
                val kodTovara = cursor.getInt(cursor.getColumnIndex("КодТовара"))
                val planPostavka = cursor.getInt(cursor.getColumnIndex("ПлановаяПоставка"))
                result += "Код заказа: $kodZakaza\n"
                result += "Наименование заказчика: $naimZ\n"
                result += "Адрес заказчика: $adresZ\n"
                result += "Телефон: $telefon\n"
                result += "Номер договора: $nomerDogovora\n"
                result += "Дата заключения договора: $dataZaklDogovora\n"
                result += "Код товара: $kodTovara\n"
                result += "Плановая поставка: $planPostavka\n\n"
            } while (cursor.moveToNext())
            zakazyTextView.text = result
        } else {
            zakazyTextView.text = "Заказов нет"
        }
        cursor.close()
    }
}
