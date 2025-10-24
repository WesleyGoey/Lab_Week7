package com.wesley.lab_week7.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesley.lab_week7.data.container.WeatherContainer
import com.wesley.lab_week7.ui.model.PanPanWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weather = MutableStateFlow(PanPanWeather())
    val weather: StateFlow<PanPanWeather?> = _weather.asStateFlow()

    fun loadWeather(cityName: String) {
        if (cityName.isBlank()) {
            _weather.value = _weather.value.copy(
                isError = true,
                errorMessage = "HTTP 404 Not Found"
            )
        }

        viewModelScope.launch {
            try{
                val weatherData = WeatherContainer().weatherRepository.getWeatherByCity(cityName)
                _weather.value = weatherData.copy(
                    isError = false,
                    errorMessage = null
                )
            } catch (e: Exception){
                _weather.value = _weather.value.copy(
                    isError = true,
                    errorMessage = "HTTP 404 Not Found"
                )
            }
        }
    }
}