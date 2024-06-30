package com.misha.apteka

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Made_otgruz : AppCompatActivity() {

    var q = 0

    var kodZakaza =""
    var dataOtgruzki=""
    var otgruzhenoTovara=""
    var kodOtgruzki=""

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_made_otgruz)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val KodO:EditText = findViewById(R.id.Ocode)
        val ZKodO:EditText = findViewById(R.id.OcodeZ)
        val DateO:EditText = findViewById(R.id.Odate)
        val otext: EditText= findViewById(R.id.OOTGR)

        val ADD: Button = findViewById(R.id.OB)

        ADD.setOnClickListener {
            q+=1
            kodOtgruzki = KodO.text.toString()
            kodZakaza = ZKodO.text.toString()
            dataOtgruzki = DateO.text.toString()
            otgruzhenoTovara = otext.text.toString()

            val db = AppDatabase(this)
            db.addOtgruzka(kodOtgruzki, kodZakaza, dataOtgruzki,otgruzhenoTovara, this)
        }
        val showotgruzButton = findViewById<Button>(R.id.pokO)

            showotgruzButton.setOnClickListener {

                val intent = Intent(this, ShowOtgruzkiActivity::class.java)
                startActivity(intent)

            }
        val delO: Button = findViewById(R.id.deleteO)
        delO.setOnClickListener {
            // Запрос подтверждения
            AlertDialog.Builder(this)
                .setTitle("Подтверждение")
                .setMessage("Вы действительно хотите удалить все записи?")
                .setPositiveButton("Да") { dialog, which ->
                    val db = AppDatabase(this)
                    db.deleteAllOtgruzki() // Вызов метода для удаления всех записей
                    Toast.makeText(this, "Все записи удалены", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Нет") { dialog, which ->
                    // Отмена удаления
                    dialog.dismiss()
                }
                .show()
        }
    }
}
