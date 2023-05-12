package com.ex.weatherapp.data.remote

import com.ex.weatherapp.data.models.CitySearchResponse
import com.ex.weatherapp.data.models.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    companion object {
        const val APP_ID = "0bea1117cb2337e9ba3d7abb7390a622"
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

//    @GET("weather")
//    suspend fun getCurrentWeatherByLatLng(
//        @Query("lat") lat: Double,
//        @Query("lon") lon: Double
//    ): Response<CurrentWeatherResponse>
//
//    @GET("weather")
//    suspend fun getCurrentWeatherByCityId(
//        @Query("id") id: Long
//    ): Response<CurrentWeatherResponse>

    @GET("find")
    suspend fun searchCities(
        @Query("q") query: String,
        @Query("appid") apiKey: String = APP_ID
    ): CitySearchResponse

    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") appId: String = APP_ID
    ): CurrentWeather
}