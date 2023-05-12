package com.ex.weatherapp.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationRepositoryImpl(private val context: Context) : LocationRepository {

    @SuppressLint("MissingPermission", "SetTextI18n")
    override fun getCurrentLocation(): Flow<LatLng> = flow {

        emit(suspendCancellableCoroutine { cancellableContinuation ->
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token,
            ).addOnSuccessListener { location ->
                if (location != null) {
                    cancellableContinuation.resume(LatLng(location.latitude, location.longitude))
                }
            }.addOnFailureListener { exception ->
                cancellableContinuation.resumeWithException(exception)
            }.addOnCompleteListener {
                cancellableContinuation.cancel()
            }.addOnCanceledListener {
                cancellableContinuation.cancel()
            }

            cancellableContinuation.invokeOnCancellation {
                cancellationTokenSource.cancel()
            }
        })

    }


}