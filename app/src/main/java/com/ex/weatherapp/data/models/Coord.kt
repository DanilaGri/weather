package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Coord(
    /**
     * City geo location, latitude
     */
    @SerializedName("lon")
    val lon: Double? = null,
    /**
     * City geo location, longitude
     */
    @SerializedName("lat")
    val lat: Double? = null
)