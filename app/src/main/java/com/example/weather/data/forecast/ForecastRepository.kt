package com.example.weather.data.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipCode: String) {
        val randomValues = List(7) { Random.nextFloat() * 100 }
        val forecastItems = randomValues.map { temperature ->
            DailyForecast(temperature, getDescription(temperature))
        }
        _weeklyForecast.value = forecastItems
    }

    private fun getDescription(temperature: Float): String {
        return when (temperature) {
            in Float.MIN_VALUE.rangeTo(32f) -> "Snowing"
            in 32f.rangeTo(52f) -> "Thunderstorms"
            in 52f.rangeTo(62f) -> "Raining"
            in 62f.rangeTo(72f) -> "Sun & Rain"
            in 72f.rangeTo(82f) -> "Cloudy"
            in 82f.rangeTo(92f) -> "Partly Sunny"
            in 92f.rangeTo(100f) -> "Partly Cloudy"
            in 100f.rangeTo(Float.MAX_VALUE) -> "Sunny"
            else -> "Does not compute"
        }
    }
}