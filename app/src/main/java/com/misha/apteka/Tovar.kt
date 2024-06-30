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

class Tovar : AppCompatActivity() {
    var k = 0
    var naimT = ""
    var kodT =""
    var cenaT =""
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tovar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val NaimT: EditText = findViewById(R.id.NameT)
        val KodT: EditText = findViewById(R.id.KodT)
        val CenaT: EditText = findViewById(R.id.CenaT)


        val ADD_T: Button = findViewById(R.id.add_Tback)
        ADD_T.setOnClickListener {
            k += 1
            naimT = NaimT.text.toString()
            kodT = KodT.text.toString()
            cenaT = CenaT.text.toString()

            val db = AppDatabase(this)
            db.addTovar(naimT, kodT, cenaT, this)
        }
        val showTovaryButton = findViewById<Button>(R.id.showTovaryButton)

            showTovaryButton.setOnClickListener {

                    val intent = Intent(this, ShowTovaryActivity::class.java)
                    startActivity(intent)

            }
        val delT:Button = findViewById(R.id.deleteT)
        delT.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Подтверждение")
                .setMessage("Вы действительно хотите удалить все товары?")
                .setPositiveButton("Да") { dialog, which ->
                    val db = AppDatabase(this)
                    db.deleteAllTovary()
                    Toast.makeText(this, "Все товары удалены", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Нет") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }

    }
}
