package com.wesley.lab_week7.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesley.lab_week7.data.container.WeatherContainer
import com.wesley.lab_week7.data.dto.Weather
import com.wesley.lab_week7.ui.model.PanPanWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val _weather = MutableStateFlow(PanPanWeather())
    val weather: StateFlow<PanPanWeather?> = _weather.asStateFlow()

    fun loadWeather(cityName: String){
        viewModelScope.launch{
            _weather.value = _weather.value.copy(
                isError = false,
                errorMessage = null
            )
        }
    }
}