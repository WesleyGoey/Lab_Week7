package com.wesley.lab_week7.data.repository

import com.wesley.lab_week7.data.service.WeatherService
import com.wesley.lab_week7.ui.model.PanPanWeather
import java.io.IOException

class WeatherRepository (private val service: WeatherService) {
    suspend fun getWeatherByCity(cityName:String): PanPanWeather{
        val weather = service.getWeather(
            city = cityName,
            apiKey = "c98f91bafdefa4e1a57e2598501305e0",
            units = "metric"
        ).body()?: throw IOException("Empty response from weather service")

        return PanPanWeather(
            city = weather.name,
            dateTime = weather.dt,
            icon = weather.weather[0].icon,
            condition = weather.weather[0].main,
            temperature = weather.main.temp,
            humidity = weather.main.humidity,
            wind = weather.wind.speed,
            feelsLike = weather.main.feels_like,
            rainFall = weather.rain?.`1h` ?: 0.0,
            pressure = weather.main.pressure,
            clouds = weather.clouds.all,
            sunrise = weather.sys.sunrise,
            sunset = weather.sys.sunset,
            isError = false,
            errorMessage = null
        )
    }
}