package com.yasinsenel.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.yasinsenel.weatherforecast.databinding.FragmentMainScreenBinding
import com.yasinsenel.weatherforecast.model.WeatherResponseModel
import com.yasinsenel.weatherforecast.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainScreenFragment : Fragment() {
    private lateinit var binding : FragmentMainScreenBinding
    private val weatherViewModel : WeatherViewModel by viewModels()

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
            }
        }
    }


}