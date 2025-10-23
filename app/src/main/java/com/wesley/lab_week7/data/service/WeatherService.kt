package com.wesley.lab_week7.data.service

import com.wesley.lab_week7.data.dto.ResponseWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Response<ResponseWeatherData>
}