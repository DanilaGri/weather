package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class CitySearchResponse(
    @SerializedName("list")
    val cities: List<City>
)