package com.yasinsenel.weatherforecast.model

data class AirQuality(
    val co: Double,
    val gb_defra_index: Int,
    val no2: Double,
    val o3: Int,
    val pm10: Double,
    val pm2_5: Double,
    val so2: Double,
    val us_epa_index: Int
)