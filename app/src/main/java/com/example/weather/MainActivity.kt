package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editZipCode: EditText = findViewById(R.id.editZipCode)
        val submitButton: Button = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            if (editZipCode.text.toString().length != 5 )
                Toast.makeText(
                    this,
                    "Your zip code does not match",
                    Toast.LENGTH_SHORT)
                    .show()
            else Toast.makeText(
                this,
                "Your zip code has successfully added",
                Toast.LENGTH_SHORT).show()
        }
    }
}