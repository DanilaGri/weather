package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class WeatherModel(
    /**
     * Weather condition id
     */
     @SerializedName("id")
    val id: Long? = null,

    /**
     * Group of weather parameters (Rain, Snow, Extreme etc.)
     */
     @SerializedName("main")
    val main: String? = null,

    /**
     * Weather condition within the group
     */
     @SerializedName("description")
    val description: String? = null,

    /**
     * Weather icon id
     */
     @SerializedName("icon")
    val icon: String? = null
)