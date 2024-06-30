package com.misha.apteka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val Add_zakaz: Button = findViewById(R.id.add_zakaz)
        val Add_tovar: Button = findViewById(R.id.add_tovar)
        val Add_otgruz: Button = findViewById(R.id.Made_otgruz)
        Add_zakaz.setOnClickListener {
            val z_intent = Intent(this, Zakaz::class.java)
            startActivity(z_intent)
        }
        Add_tovar.setOnClickListener {
            val t_intent = Intent(this, Tovar::class.java)
            startActivity(t_intent)
        }
        Add_otgruz.setOnClickListener {
            val o_intent = Intent(this, Made_otgruz::class.java)
            startActivity(o_intent)
        }
    }
}