package com.yasinsenel.weatherforecast.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yasinsenel.weatherforecast.databinding.WeatherItemsLayoutBinding
import com.yasinsenel.weatherforecast.model.WeatherResponseModel

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.Holder>() {
    private val weatherList : ArrayList<WeatherResponseModel> = arrayListOf()
    inner class Holder(val binding : WeatherItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    private fun fetchList(){

    }
}