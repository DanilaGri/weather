package com.ex.weatherapp.data.repositories

import com.ex.weatherapp.data.local.CityDao
import com.ex.weatherapp.data.models.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(private val cityDao: CityDao){

    fun getAllCity(): Flow<List<City>> {
        return cityDao.getAllCity()//.map { it.toExternal() }
    }

    suspend fun deleteCity(city: City){
        cityDao.deleteCity(city)
    }

    suspend fun addCity(city: City) {
        cityDao.insertCity(city)
    }

    fun getCityByName(name:String): Flow<City?> {
        return cityDao.getCityByName(name)
    }

}