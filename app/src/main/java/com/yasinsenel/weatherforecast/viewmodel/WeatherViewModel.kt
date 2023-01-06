package com.yasinsenel.weatherforecast.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import com.yasinsenel.weatherforecast.repository.WeatherRepository
import com.yasinsenel.weatherforecast.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel(){

    val weatherDataResponse =MutableLiveData<WeatherResponseModel?>()

    fun refreshWeatherData(cityName: String){
        getWeatherData(cityName)
    }

    fun getWeatherData(cityName : String) {
        viewModelScope.launch {
            val result = repository.getWeatherResponse(cityName)
            when(result){
                is Resource.Success->{
                    weatherDataResponse.value = result.data
                }
                is Resource.Loading ->{
                    println("yukleniyor")
                }
                is Resource.Error -> {
                    println("hata")
                }
            }
        }
    }
}