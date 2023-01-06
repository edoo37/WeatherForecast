package com.yasinsenel.weatherforecast.repository

import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import com.yasinsenel.weatherforecast.service.WeatherAPI
import com.yasinsenel.weatherforecast.utils.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherAPI) {

    suspend fun getWeatherResponse(cityName : String) : Resource<WeatherResponseModel>{
        val response =try{
            api.getData(cityName)
        }
        catch (e : Exception){
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}