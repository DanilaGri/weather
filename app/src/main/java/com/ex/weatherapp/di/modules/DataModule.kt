package com.ex.weatherapp.di.modules

import android.content.Context
import com.ex.weatherapp.data.local.AppDatabase
import com.ex.weatherapp.data.local.CityDao
import com.ex.weatherapp.data.remote.WeatherService
import com.ex.weatherapp.data.repositories.LocationRepository
import com.ex.weatherapp.data.repositories.LocationRepositoryImpl
import com.ex.weatherapp.data.repositories.WeatherRepository
import com.ex.weatherapp.data.repositories.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideCityDao(database: AppDatabase): CityDao {
        return database.cityDao()
    }

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext appContext: Context) = AppDatabase.getInstance(appContext)

    @Provides
    @Singleton
    fun provideLocationRepository(
       @ApplicationContext appContext: Context
    ): LocationRepository = LocationRepositoryImpl(appContext)

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherService: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(weatherService)
}