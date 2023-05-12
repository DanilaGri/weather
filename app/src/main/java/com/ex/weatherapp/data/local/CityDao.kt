package com.ex.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ex.weatherapp.data.models.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * FROM cities")
    fun getAllCity(): Flow<List<City>>

    @Insert(onConflict = IGNORE)
    suspend fun insertCity(city: City): Long

    @Update
    suspend fun updateCity(city: City)

    @Delete
    suspend fun deleteCity(city: City)

    @Transaction
    suspend fun upsert(city: City) {
        insertCity(city)
            .takeIf {
                it == -1L
            }?.let { updateCity(city) }
    }

    @Query("SELECT * FROM cities WHERE name=:name")
    fun getCityByName(name: String): Flow<City?>
}