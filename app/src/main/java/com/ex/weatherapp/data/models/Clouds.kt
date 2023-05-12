package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Clouds(
    /**
     * Cloudiness, %
     */
    @SerializedName("all")
    val all: Long? = null
)