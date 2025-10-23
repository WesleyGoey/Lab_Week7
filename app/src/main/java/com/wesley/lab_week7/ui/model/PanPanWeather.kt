package com.wesley.lab_week7.ui.model

data class PanPanWeather(
    val city: String,
    val updatedDate: String,
    val updatedTime: String,
    val temperature: Double,
    val condition: String,
    val icon: String? = null,
    val humidity: Int,
    val wind: Double,
    val feelsLike: Double,
    val rainFall: Double,
    val pressure: Int,
    val clouds: Int,
    val sunrise: String,
    val sunset: String
)
