package com.misha.apteka

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Made_otgruz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_made_otgruz)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val KodO: EditText = findViewById(R.id.Ocode)
        val ZKodO: EditText = findViewById(R.id.OcodeZ)
        val DateO: EditText = findViewById(R.id.DateZ)
        val otext: EditText = findViewById(R.id.OOTGR)
        val add: Button = findViewById(R.id.OB)

        add.setOnClickListener {
            // Получаем данные из EditText
            val kodOtgruzki = KodO.text.toString().toIntOrNull()
            val kodZakaza = ZKodO.text.toString().toIntOrNull()
            val dataOtgruzki = DateO.text.toString().toDate()
            val otgruzhenoTovara = otext.text.toString().toIntOrNull()

            // Проверка на корректность ввода (можно добавить более строгие проверки)
            if (kodOtgruzki != null && kodZakaza != null && otgruzhenoTovara != null) {
                // Получаем экземпляр AptekaDbHelper
                val dbHelper = MYDbHelper(this)
                // Открываем базу данных для записи
                val db = dbHelper.writableDatabase

                // Создаем ContentValues для добавления данных в базу
                val values = ContentValues().apply {
                    put("КодОтгрузки", kodOtgruzki)
                    put("КодЗаказа", kodZakaza)
                    if (dataOtgruzki != null) {
                        put("ДатаОтгрузки", dataOtgruzki.toSqlDateString())
                    }
                    put("ОтгруженоТовара", otgruzhenoTovara)
                }

                // Вставляем данные в таблицу "Отгрузки"
                val newRowId = db.insert("Отгрузки", null, values)

                // Закрываем базу данных
                db.close()

                // Вывод сообщения об успешной записи данных
                // (Можно добавить обработку ошибок)
                // ...
            } else {
                // Вывод сообщения об ошибке ввода данных
                // ...
            }
        }
    }

    // Функция для преобразования строки в дату
    private fun String.toDate(): Date? {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return try {
            formatter.parse(this)
        } catch (e: Exception) {
            null
        }
    }

    // Функция для преобразования даты в строку в формате SQL
    private fun Date.toSqlDateString(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(this)
    }

    class MYDbHelper(context: Context) : SQLiteOpenHelper(context, "prog.db", null, 1) {
        override fun onCreate(db: SQLiteDatabase) {
            // Создайте таблицы (в этом случае они уже созданы)
            db.execSQL("CREATE TABLE IF NOT EXISTS Товары (" +
                    "КодТовара INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "НаименованиеТовара TEXT," +
                    "ЦенаТовара REAL" +
                    ");")

            db.execSQL("CREATE TABLE IF NOT EXISTS Заказы (" +
                    "КодЗаказа INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "НаименованиеЗаказчика TEXT," +
                    "АдресЗаказчика TEXT," +
                    "Телефон TEXT," +
                    "НомерДоговора INTEGER," +
                    "ДатаЗаключениеДоговора TEXT," + // Храним дату как текст
                    "КодТовара INTEGER," +
                    "ПлановаяПоставка INTEGER," +
                    "FOREIGN KEY (КодТовара) REFERENCES Товары(КодТовара)" + // Добавляем внешний ключ
                    ");")

            // Создаем таблицу "Отгрузки"
            db.execSQL("CREATE TABLE IF NOT EXISTS Отгрузки (" +
                    "КодОтгрузки INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "КодЗаказа INTEGER," +
                    "ДатаОтгрузки TEXT," +
                    "ОтгруженоТовара INTEGER," +
                    "FOREIGN KEY (КодЗаказа) REFERENCES Заказы(КодЗаказа)" + // Добавляем внешний ключ
                    ");")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            // Если необходимо обновить таблицы, вы можете добавить код сюда.
            // Например, для удаления старой таблицы и создания новой:
            // db.execSQL("DROP TABLE IF EXISTS Отгрузки")
            // onCreate(db)
        }
    }
}
