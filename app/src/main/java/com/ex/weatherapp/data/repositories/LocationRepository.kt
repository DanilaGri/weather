package com.ex.weatherapp.data.repositories

import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow


interface LocationRepository {
    fun getCurrentLocation(): Flow<LatLng>
}