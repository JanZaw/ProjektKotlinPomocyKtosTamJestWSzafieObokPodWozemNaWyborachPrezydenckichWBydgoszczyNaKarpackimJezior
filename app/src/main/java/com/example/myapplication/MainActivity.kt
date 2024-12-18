package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imieField: EditText = findViewById(R.id.et_imie)
        val nazwiskoField: EditText = findViewById(R.id.et_nazwisko)
        val wiekField: EditText = findViewById(R.id.num_wiek)
        val wysokoscField: EditText = findViewById(R.id.num_wysokosc)
        val wagaField: EditText = findViewById(R.id.num_waga)
        val zapiszButton: Button = findViewById(R.id.btn_zapisz)
        val ekranButton: Button = findViewById(R.id.btn_ekran2)


        zapiszButton.setOnClickListener {

        }


        ekranButton.setOnClickListener {
            val intent = Intent(this, ListAcitivity::class.java)
            startActivity(intent)

        }
    }
}
