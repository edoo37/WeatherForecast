package com.yasinsenel.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
        binding.edtextSearch.setOnClickListener {

        }
        binding.edtextSearch.addTextChangedListener {
           weatherViewModel.refreshWeatherData(it.toString())
        }
    }

    fun setView(){
        binding.apply {
            tvName.setText(weatherResponseModel.name)
            tvTempDegree.text = weatherResponseModel.main?.temp.toString() + " °C"
            tvWeatherMain.setText(weatherResponseModel.weather?.get(0)?.main)
            tvWeatherMainDesc.setText(weatherResponseModel.weather?.get(0)?.description)
            Glide.with(requireContext())
                .load("https://openweathermap.org/img/wn/" + weatherResponseModel.weather?.get(0)?.icon+"@2x.png")
                .into(ivWeatherIcon)
            tvTemp.text = "Sıcaklık : " + weatherResponseModel.main?.temp.toString() + " °C"
            tvFeelsTemp.text = "Hissedilen Sıcaklık : " + weatherResponseModel.main?.feels_like.toString() + " °C"
            tvMaxTemp.text = "Maksimum Sıcaklık : " + weatherResponseModel.main?.temp_max.toString() + " °C"
            tvMinTemp.text = "Minimum Sıcaklık : " + weatherResponseModel.main?.temp_min.toString() + " °C"
            tvPressure.text = "Basınç : " + weatherResponseModel.main?.pressure.toString() + " hPa"
            tvHumidity.text = "Nem : " + weatherResponseModel.main?.humidity.toString() + " %"
            tvSeaLevel.text = "Deniz Seviyesi : " + weatherResponseModel.main?.sea_level.toString() + " hPa"
            tvGrndLevel.text = "Yer Seviyesi : " + weatherResponseModel.main?.grnd_level.toString() + " hPa"
            tvWindSpeed.text = "Rüzgar Hızı : " + weatherResponseModel.wind?.speed.toString() + " m/sn"
            tvWindDegree.text = "Rüzgar Yönü : " + weatherResponseModel.wind?.deg.toString() + "derece"
            tvCloudy.text = "Bulut Yüzdesi : " + weatherResponseModel.clouds?.all.toString() + " %"
         }
    }

}