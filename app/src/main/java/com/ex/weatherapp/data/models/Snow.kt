package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Snow(
    /**
     * Snow volume for last 3 hours
     */
    @SerializedName("3h")
    val _3h: Double? = null
)