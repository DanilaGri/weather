package com.ex.weatherapp.data.models

import com.google.gson.annotations.SerializedName

class Wind(
    /**
     * Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
     */
     @SerializedName("speed")
    val speed: Double? = null,

    /**
     *  Wind direction, degrees (meteorological)
     */
     @SerializedName("deg")
    val deg: Double? = null
)