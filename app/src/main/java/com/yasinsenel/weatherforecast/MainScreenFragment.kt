package com.yasinsenel.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.yasinsenel.weatherforecast.databinding.FragmentMainScreenBinding
import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import com.yasinsenel.weatherforecast.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


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
                println(it.name)
                weatherResponseModel = it
                setView()
            }
        }
    }

    fun setView(){
        binding.apply {
            tvName.setText(weatherResponseModel.name)
            tvTempDegree.setText(weatherResponseModel.main?.temp.toString())
            tvWeatherMain.setText(weatherResponseModel.weather?.get(0)?.main)
            tvWeatherMainDesc.setText(weatherResponseModel.weather?.get(0)?.description)
            Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weatherResponseModel.weather?.get(0)?.icon+"@2x.png")
                .into(ivWeatherIcon)
        }
    }

}