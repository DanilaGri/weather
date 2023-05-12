package com.ex.weatherapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    /**
     * Id of city
     */
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Long = 0,

    /**
     * Name of city
     */
    @ColumnInfo("name")
    val name: String = "",

    /**
     * Latitude of city
     */
    @ColumnInfo("lat")
    val lat: Double = Double.NEGATIVE_INFINITY,

    /**
     * Longitude of city
     */
    @ColumnInfo("lng")
    val lng: Double = Double.NEGATIVE_INFINITY,

)