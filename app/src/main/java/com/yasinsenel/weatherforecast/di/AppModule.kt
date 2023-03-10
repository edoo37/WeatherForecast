package com.yasinsenel.weatherforecast.di

import com.yasinsenel.weatherforecast.service.WeatherAPI
import com.yasinsenel.weatherforecast.utils.Constats
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectRetrofit() : WeatherAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constats.BASE_URL)
            .build()
            .create(WeatherAPI::class.java)
    }
}