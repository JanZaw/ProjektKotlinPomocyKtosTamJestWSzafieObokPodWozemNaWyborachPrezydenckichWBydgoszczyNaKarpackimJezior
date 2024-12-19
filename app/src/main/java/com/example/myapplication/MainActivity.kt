package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
            val imieText = imieField.text.toString().trim()
            val nazwiskoText = nazwiskoField.text.toString().trim()
            val wiekText = wiekField.text.toString()
            val wysokoscText = wysokoscField.text.toString()
            val wagaText = wagaField.text.toString()

            if (imieText.isEmpty() || nazwiskoText.isEmpty() ||
                wiekText.isEmpty() || wysokoscText.isEmpty() || wagaText.isEmpty()
            ) {
                Toast.makeText(this, "Wszystkie pola są wymagane", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (wiekText.toInt() < 0) {
                Toast.makeText(this, "Wiek musi być większy niż 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (wysokoscText.toInt() !in 50..250) {
                Toast.makeText(this, "Wzrost musi być w zakresie 50-250 cm", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (wagaText.toInt() !in 3..200) {
                Toast.makeText(this, "Waga musi być w zakresie 3-200 kg", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveData(
                this,
                imieText,
                nazwiskoText,
                wiekText.toInt(),
                wysokoscText.toInt(),
                wagaText.toInt()
            )
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
        }

        ekranButton.setOnClickListener {
            val intent = Intent(this, ListAcitivity::class.java)
            startActivity(intent)
        }
    }
}
