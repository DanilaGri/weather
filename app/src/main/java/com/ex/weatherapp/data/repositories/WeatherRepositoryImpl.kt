package com.ex.weatherapp.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.ui.text.intl.Locale
import com.ex.weatherapp.data.models.CitySearchResponse
import com.ex.weatherapp.data.models.CurrentWeather
import com.ex.weatherapp.data.remote.WeatherService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository{

    override fun getCurrentWeatherByCity(city: String): Flow<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentWeatherByLocation(latLng: LatLng): CurrentWeather {
        return weatherService.getWeatherByLocation(
            latitude = latLng.latitude,
            longitude = latLng.longitude,
            lang = Locale.current.language
        )
    }

    override suspend fun searchCities(query: String): CitySearchResponse {
        return weatherService.searchCities(query)
    }


}