package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Main(
     @SerializedName("temp")
    val temp: Double? = null,
     @SerializedName("pressure")
    val pressure: Double? = null,
     @SerializedName("humidity")
    val humidity: Long? = null,
     @SerializedName("temp_min")
    val tempMin: Double? = null,
     @SerializedName("temp_max")
    val tempMax: Double? = null
)