package com.wesley.lab_week7.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesley.lab_week7.R
import com.wesley.lab_week7.data.container.WeatherContainer
import com.wesley.lab_week7.ui.model.PanPanWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _searchCity = MutableStateFlow("")
    val searchCity: StateFlow<String> = _searchCity.asStateFlow()

    private val _weather = MutableStateFlow(PanPanWeather())
    val weather: StateFlow<PanPanWeather> = _weather.asStateFlow()

    private val _allCard = MutableStateFlow<List<Triple<Int, String, String>>>(emptyList())
    val allCard: StateFlow<List<Triple<Int, String, String>>> = _allCard.asStateFlow()

    fun searchCity(cityName: String) {
        _searchCity.value = cityName
    }

    fun buttonSearchCity() {
        val cityName = _searchCity.value
        loadWeather(cityName)
    }

    fun loadWeather(cityName: String) {
        if (cityName.isBlank()) {
            _weather.value = _weather.value.copy(
                isError = true,
                errorMessage = "HTTP 404 Not Found"
            )
            _allCard.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                val weatherData = WeatherContainer().weatherRepository.getWeatherByCity(cityName)
                _weather.value = weatherData.copy(
                    isError = false,
                    errorMessage = null
                )
                loadAllCard()
            } catch (e: Exception) {
                _weather.value = _weather.value.copy(
                    isError = true,
                    errorMessage = "HTTP 404 Not Found"
                )
            }
        }
    }

    fun loadAllCard() {
        val weatherData = _weather.value
        val cardList = listOf(
            Triple(R.drawable.icon_humidity, "Humidity", "${weatherData.humidity} %"),
            Triple(R.drawable.icon_wind, "Wind", "${weatherData.wind} m/s"),
            Triple(R.drawable.icon_feels_like, "Feels Like", "${weatherData.feelsLike} °C"),
            Triple(R.drawable.vector_2, "Rain Fall", "${weatherData.rainFall} mm"),
            Triple(R.drawable.devices, "Pressure", "${weatherData.pressure} hPa"),
            Triple(R.drawable.cloud, "Clouds", "${weatherData.clouds} %")
        )
        _allCard.value = cardList
    }
}