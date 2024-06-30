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

class Zakaz : AppCompatActivity() {
    var w = 0
    var f: Int = 0
    var naimZ = ""
    var kodZakaza = ""
    var adresZ = ""
    var telefon = ""
    var nomerDogovora = ""
    var dataZaklDogovora = ""
    var kodTovara = ""
    var planPostavka = ""
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_zakaz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val NaimZ: EditText = findViewById(R.id.NaimZ)
        val PlanpostZ: EditText = findViewById(R.id.PlanpostZ)
        val KodZ: EditText = findViewById(R.id.KodZ)
        val NaimZZ: EditText = findViewById(R.id.NaimZZ)
        val AdresZ: EditText = findViewById(R.id.AdresZ)
        val NumZ: EditText = findViewById(R.id.NumZ)
        val NumDZ: EditText = findViewById(R.id.NumDZ)
        val DateZ: EditText = findViewById(R.id.DateZ)
        val KodTZ: EditText = findViewById(R.id.KodTZ)

        val ADD_Z: Button = findViewById(R.id.Add_Zback)
        ADD_Z.setOnClickListener {
            w+=1
            naimZ = NaimZ.text.toString()
            kodZakaza = KodZ.text.toString()
            adresZ = AdresZ.text.toString()
            telefon = NumZ.text.toString()
            nomerDogovora = NumDZ.text.toString()
            dataZaklDogovora = DateZ.text.toString()
            kodTovara = KodTZ.text.toString()
            planPostavka = PlanpostZ.text.toString()



            val db = AppDatabase(this)
            db.addZakaz(naimZ, adresZ, telefon, nomerDogovora, dataZaklDogovora, kodTovara, planPostavka, this)
        }

        val showZakazButton = findViewById<Button>(R.id.pokZ)

            showZakazButton.setOnClickListener {

                val intent = Intent(this, ShowZakazyActivity::class.java)
                startActivity(intent)
                }
            val delz: Button = findViewById(R.id.deleteZ)

        delz.setOnClickListener {
            // Запрос подтверждения
            AlertDialog.Builder(this)
                .setTitle("Подтверждение")
                .setMessage("Вы действительно хотите удалить все заказы?")
                .setPositiveButton("Да") { dialog, which ->
                    val db = AppDatabase(this)
                    db.deleteAllZakazy()
                    Toast.makeText(this, "Все заказы удалены", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Нет") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
            }


    }
