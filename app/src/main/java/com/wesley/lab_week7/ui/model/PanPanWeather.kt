package com.wesley.lab_week7.ui.model

data class PanPanWeather(
    val city: String = "",
    val dateTime: Int = 0,
    val icon: String? = null,
    val condition: String = "",
    val temperature: Double = 0.0,
    val humidity: Int = 0,
    val wind: Double = 0.0,
    val feelsLike: Double = 0.0,
    val rainFall: Double = 0.0,
    val pressure: Int = 0,
    val clouds: Int = 0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val isError:Boolean = false,
    val errorMessage:String? = null
)
