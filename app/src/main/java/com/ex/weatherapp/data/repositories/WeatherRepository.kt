package com.ex.weatherapp.data.repositories

import com.ex.weatherapp.data.models.CitySearchResponse
import com.ex.weatherapp.data.models.CurrentWeather
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface WeatherRepository {
    fun getCurrentWeatherByCity(city: String): Flow<CurrentWeather>

    suspend fun getCurrentWeatherByLocation(latLng: LatLng): CurrentWeather

    suspend fun searchCities(query: String): CitySearchResponse
}