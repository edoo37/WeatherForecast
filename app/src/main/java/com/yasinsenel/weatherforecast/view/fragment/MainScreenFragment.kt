package com.yasinsenel.weatherforecast.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.orhanobut.hawk.Hawk
import com.yasinsenel.weatherforecast.R
import com.yasinsenel.weatherforecast.adapter.WeatherAdapter
import com.yasinsenel.weatherforecast.databinding.FragmentMainScreenBinding
import com.yasinsenel.weatherforecast.model.Forecastday
import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import com.yasinsenel.weatherforecast.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class MainScreenFragment : Fragment() {
    private lateinit var binding : FragmentMainScreenBinding
    private val weatherViewModel : WeatherViewModel by viewModels()

    private var weatherResponseModel =  WeatherResponseModel()
    private var weatherAdapter = WeatherAdapter()

    private val weatherList : ArrayList<Forecastday> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Hawk.init(requireContext()).build()

        initAdapter()
        /*var getData : String? = null
        val bundleData = arguments
        getData = bundleData?.getString("City")*/

        var cityName : String = Hawk.get("City", "kastamonu")

        binding.swipeRefreshLayout.setOnRefreshListener {
            weatherViewModel.refreshWeatherData(cityName)
            binding.swipeRefreshLayout.isRefreshing = false
        }


       binding.ivSearch.setOnClickListener {
           Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_searchFragment)
       }

        weatherViewModel.getWeatherData(cityName)

        weatherViewModel.weatherDataResponse.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(requireContext(),"Basarılı",Toast.LENGTH_SHORT).show()
                println(it.current?.last_updated)
                weatherResponseModel = it
                println(it.location?.name)

            }
            setView()
            fetchList()
        }

    }


    fun setView(){
        binding.apply {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale("tr"))
            val date = sdf.parse(weatherResponseModel.location!!.localtime)
            sdf.applyPattern("EEE, MMMM d, yyyy")
            val str = sdf.format(date)
            tvDay.text = str

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
    fun initAdapter(){
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = weatherAdapter
        }
    }

    private fun fetchList(){
        weatherList.clear()
        weatherResponseModel?.let {
            it.forecast?.forecastday?.forEach {
                weatherList.add(it)
            }
        }
        weatherAdapter.setList(weatherList)
    }

}