package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Sys(
     @SerializedName("type")
    val type: Long? = null,
     @SerializedName("weatherId")
    val id: Long? = null,
     @SerializedName("message")
    val message: Double? = null,
     @SerializedName("country")
    val country: String? = null,
     @SerializedName("sunrise")
    val sunrise: Long? = null,
     @SerializedName("sunset")
    val sunset: Long? = null
)