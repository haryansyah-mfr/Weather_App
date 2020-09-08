package com.example.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.forecast.DailyForecast

class DailyForecastAdapter(
    private val clickListener: (DailyForecast) -> Unit
) : ListAdapter<DailyForecast, DailyForecastViewHolder>(DIFF_CONFIG) {
    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<DailyForecast>() {
            override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        return DailyForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler_view, parent, false))
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        holder.bindItem(getItem(position))
        holder.itemView.setOnClickListener {
            clickListener(getItem(position))
        }
    }
}

class DailyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val temperature: TextView = view.findViewById(R.id.temperature_view)
    private val description: TextView = view.findViewById(R.id.description_view)

    fun bindItem(dailyForecast: DailyForecast) {
        temperature.text = tempFormat(dailyForecast.temperature)
        description.text = dailyForecast.description
    }

    private fun tempFormat(temperature: Float): String {
        return if (String.format("%.1f", temperature).endsWith(",0"))
            "${temperature.toInt()}    °F"
        else String.format("%.1f °F", temperature)
    }
}