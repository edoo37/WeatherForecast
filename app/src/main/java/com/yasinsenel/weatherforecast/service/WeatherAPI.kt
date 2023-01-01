package com.yasinsenel.weatherforecast.service

import com.yasinsenel.weatherforecast.model.WeathetResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/weather?&units=metric&APPID=a8827bb0c50754b83dfb65fba635951a")
    fun getData(@Query("q") cityName : String?) : Response<WeathetResponseModel>
}