package com.wesley.lab_week7.data.container

import com.google.gson.GsonBuilder
import com.wesley.lab_week7.data.repository.WeatherRepository
import com.wesley.lab_week7.data.service.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherContainer {
    companion object {
        val BASE_URL = "https://api.openweathermap.org/"
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .build()

    private val weatherService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }

    val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherService)
    }

}