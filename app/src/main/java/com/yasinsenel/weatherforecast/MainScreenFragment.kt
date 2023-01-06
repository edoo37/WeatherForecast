package com.yasinsenel.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yasinsenel.weatherforecast.databinding.FragmentMainScreenBinding
import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import com.yasinsenel.weatherforecast.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class MainScreenFragment : Fragment() {
    private lateinit var binding : FragmentMainScreenBinding
    private val weatherViewModel : WeatherViewModel by viewModels()

    private var weatherResponseModel =  WeatherResponseModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.getWeatherData("istanbul")

        weatherViewModel.weatherDataResponse.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(),"Basarılı",Toast.LENGTH_SHORT).show()
                println(it.current?.last_updated)
                weatherResponseModel = it
                setView()
            }
        }
       /* binding.edtextSearch.setOnClickListener {

        }
        binding.edtextSearch.addTextChangedListener {
           weatherViewModel.refreshWeatherData(it.toString())
        }*/
    }

    fun setView(){
        binding.apply {

            val dtStart = "2010-10-15"
            val format = SimpleDateFormat("yyyy-MM-dd")
            try {
                val date: Date = format.parse(dtStart)
                System.out.println(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            tvCityName.text = weatherResponseModel.location?.name
            tvCountry.text = weatherResponseModel.location?.country
            tvCurrentTemp.text = weatherResponseModel.current?.temp_c.toString() + " °C"
            Glide.with(requireContext())
                .load("https:"+weatherResponseModel.current?.condition?.icon)
                .into(ivWeatherIcon)
            tvWindStatus2.text = weatherResponseModel.current?.wind_kph.toString() + " km/h"
            tvHumidityStatus2.text = weatherResponseModel.current?.humidity.toString() + " %"
            tvAirPressureStatus2.text = weatherResponseModel.current?.pressure_mb.toString() + " mbar"
            tvVisibilityStatus2.text = weatherResponseModel.current?.vis_km.toString() + " km"
            weatherResponseModel.forecast?.forecastday?.get(1)?.day?.condition?.icon
         }
    }

}