package com.yasinsenel.weatherforecast.model

data class WeatherResponseModel(
    val alerts: Alerts? = null,
    val current: Current? = null,
    val forecast: Forecast? = null,
    val location: Location? = null
)