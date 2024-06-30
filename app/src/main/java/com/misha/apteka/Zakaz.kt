package com.misha.apteka

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Zakaz : AppCompatActivity() {
    var f: Int = 0
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
        val planpostZ = PlanpostZ.text.toString()
    }
}