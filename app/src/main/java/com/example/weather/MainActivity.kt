package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.forecast.ForecastRepository

class MainActivity : AppCompatActivity() {
    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editZipCode: EditText = findViewById(R.id.editZipCode)
        val submitButton: Button = findViewById(R.id.submitButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val dailyForecastAdapter = DailyForecastAdapter {
            Toast.makeText(
                this,
                "Has been clicked",
                Toast.LENGTH_SHORT).show()
        }

        submitButton.setOnClickListener {
            val zipCode = editZipCode.text.toString()

            if (zipCode.length != 5 )
                Toast.makeText(
                    this,
                    "Your zip code does not match",
                    Toast.LENGTH_SHORT)
                    .show()
            else forecastRepository.loadForecast(zipCode)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = dailyForecastAdapter

        forecastRepository.weeklyForecast.observe(this, { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
        })
    }
}