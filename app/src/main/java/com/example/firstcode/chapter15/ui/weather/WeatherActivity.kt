package com.example.firstcode.chapter15.ui.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.firstcode.R
import com.example.firstcode.chapter15.logic.model.Weather
import com.example.firstcode.chapter15.logic.model.getSky
import com.example.firstcode.databinding.ActivityListViewBinding
import com.example.firstcode.databinding.ActivityWeatherBinding
import com.example.firstcode.databinding.ForecastItemBinding
import com.example.firstcode.other.toast
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private val simpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }

    private lateinit var viewBinding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (viewModel.locationLng.isEmpty()){
            viewModel.locationLng = intent.getStringExtra("location_lng")?: ""
        }
        if (viewModel.locationLat.isEmpty()){
            viewModel.locationLat = intent.getStringExtra("location_lat")?: ""
        }
        if (viewModel.placeName.isEmpty()){
            viewModel.placeName = intent.getStringExtra("place_name")?: ""
        }

        viewModel.weatherLivedata.observe(this){
            val result = it.getOrNull()
            if (result != null){
                showWeatherInfo(result)
            }else{
                "无法成功获取天气信息".toast()
                it.exceptionOrNull()?.printStackTrace()
            }
        }

        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
    }

    private fun showWeatherInfo(weather: Weather) {
        val realtime = weather.realtime
        val daily = weather.daily

        //城市
        viewBinding.nowLayout.placeName.text = viewModel.placeName
        //当前温度
        val currentTempText = "${realtime.temperature.toInt()}℃"
        viewBinding.nowLayout.currentTemp.text = currentTempText
        //当前天气
        viewBinding.nowLayout.currentSky.text = getSky(realtime.skycon).info
        //空气指数
        val currentPM25Text = "空气指数${realtime.airQuality.aqi.chn.toInt()}"
        viewBinding.nowLayout.currentAOI.text = currentPM25Text
        //背景图
        viewBinding.nowLayout.nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)


        viewBinding.forecastLayout.forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days){
            val skyCon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val binding = ForecastItemBinding.bind(viewBinding.forecastLayout.forecastLayout)
            binding.dateInfo.text = simpleDateFormat.format(skyCon.date)
            binding.skyIcon.setImageResource(getSky(skyCon.value).bg)
            binding.skyInfo.text = getSky(skyCon.value).info
            binding.temperatureInfo.text = "${temperature.min.toInt()} ~ ${temperature.max.toInt()}℃"
        }

        val lifeIndex = daily.lifeIndex
        viewBinding.lifeIndexLayout.coldRiskText.text = lifeIndex.coldRisk[0].desc
        viewBinding.lifeIndexLayout.dressingText.text = lifeIndex.dressing[0].desc
        viewBinding.lifeIndexLayout.ultravioletText.text = lifeIndex.ultraviolet[0].desc
        viewBinding.lifeIndexLayout.carWashingText.text = lifeIndex.carWashing[0].desc

        viewBinding.weatherLayout.visibility = View.VISIBLE
    }
}