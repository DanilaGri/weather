package com.ex.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ex.weatherapp.data.models.City
import com.ex.weatherapp.data.models.CurrentWeather
import com.ex.weatherapp.data.repositories.CityRepository
import com.ex.weatherapp.data.repositories.LocationRepository
import com.ex.weatherapp.data.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MyUiState(
    val weather: CurrentWeather? = null,
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val error: String = ""
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyUiState())

    val uiState: StateFlow<MyUiState> = _uiState

    fun getLocation() {
        _uiState.value = uiState.value.copy(isLoading = true)
        locationRepository.getCurrentLocation().map { loc->
            kotlin.runCatching {
                weatherRepository.getCurrentWeatherByLocation(loc)
            }.onSuccess {
                _uiState.value = uiState.value.copy( weather = it, isLoading = false)
                updateFavorite()
            }.onFailure {
                _uiState.value = uiState.value.copy(isLoading = false)
                // TODO: потом
            }
        }.launchIn(viewModelScope)

    }

    fun addDelFavorite(){
        if (!uiState.value.isFavorite){
            addFavorite()
        } else {
            deleteFavorite()
        }
    }

    fun searchCities(query:String) {
        _uiState.value = uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            kotlin.runCatching {
                weatherRepository.searchCities(query)
            }.onSuccess {
                it.cities
            }.onFailure {
                _uiState.value = uiState.value.copy(isLoading = false)
                // TODO: потом
            }
        }

    }

    private fun addFavorite() = viewModelScope.launch {
        val weather = _uiState.value.weather
        weather?.let {
            val name: String? = weather.name
            val lon: Double? = weather.coord?.lon
            val lat:Double? = weather.coord?.lat
            if (name != null && lon != null && lat!=null) {
                cityRepository.addCity(City(name = name, lat = lat, lng = lat))
                _uiState.value = uiState.value.copy(isFavorite = true)

            }
        }
    }

    private fun deleteFavorite() = viewModelScope.launch {
        val weather = _uiState.value.weather
        weather?.let {
            val name: String? = weather.name
            val lon: Double? = weather.coord?.lon
            val lat:Double? = weather.coord?.lat
            if (name != null && lon != null && lat!=null) {
                cityRepository.deleteCity( City(name = name, lat = lat, lng = lat))
                _uiState.value = uiState.value.copy(isFavorite = false)
            }
        }
    }

    fun deleteFavorite(city: City) = viewModelScope.launch {
        cityRepository.deleteCity(city)
    }

    private fun updateFavorite() {
        val weather = _uiState.value.weather
        viewModelScope.launch {
            val name = weather?.name
            name?.let {
                cityRepository.getCityByName(it)
                    .collect { city ->
                        if (city != null) {
                            _uiState.value = uiState.value.copy(isFavorite = true)
                        } else {
                            _uiState.value = uiState.value.copy(isFavorite = false)
                        }
                    }
            }
        }
    }


}