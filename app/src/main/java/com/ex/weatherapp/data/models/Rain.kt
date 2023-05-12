package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Rain(
    /**
     * Rain volume for last 3 hours, mm
     */
    @SerializedName("3h")
    val _3h: Double? = null
)