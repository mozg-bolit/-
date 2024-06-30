package com.misha.apteka

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Tovar : AppCompatActivity() {
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

        var naimT: String = ""
        var kodT: String = ""
        var cenaT: Double = 0.0
        var kolvoT: Int = 0







    }
}