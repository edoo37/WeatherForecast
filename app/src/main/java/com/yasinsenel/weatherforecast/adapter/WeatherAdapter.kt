package com.yasinsenel.weatherforecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yasinsenel.weatherforecast.databinding.WeatherItemsLayoutBinding
import com.yasinsenel.weatherforecast.model.Forecastday
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.Holder>() {
    private val myweatherList : ArrayList<Forecastday> = arrayListOf()
    inner class Holder(val binding : WeatherItemsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(forecastday: Forecastday){
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale("tr"))
            val date = sdf.parse(forecastday.date)
            sdf.applyPattern("EEE")
            val str = sdf.format(date)



            binding.tvDay.text = str
            binding.tvCurrentTemp.text = forecastday.day.avgtemp_c.toString() + " °C"
            Glide.with(binding.ivWeather.context)
                .load("https:"+forecastday.day.condition.icon)
                .into(binding.ivWeather)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = WeatherItemsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = myweatherList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return myweatherList.size
    }

    fun setList(weatherList : ArrayList<Forecastday>){
        myweatherList.clear()
        myweatherList.addAll(weatherList)
        notifyDataSetChanged()
    }

    fun getDay(date: Date?): Int {
        val cal: Calendar = Calendar.getInstance()
        cal.setTime(date)
        return cal.get(Calendar.DAY_OF_MONTH)
    }

}