package com.yasinsenel.weatherforecast.service

import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("forecast.json?key=c5cf010b150a499d941162217231401&days=5&aqi=yes&alerts=yes")
    suspend fun getData(@Query("q") cityName : String?) : WeatherResponseModel
}