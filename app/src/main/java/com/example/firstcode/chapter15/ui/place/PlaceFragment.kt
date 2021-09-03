package com.example.firstcode.chapter15.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstcode.chapter15.MainActivity
import com.example.firstcode.chapter15.ui.weather.WeatherActivity
import com.example.firstcode.databinding.FragmentPlaceBinding
import com.example.firstcode.other.toast

class PlaceFragment: Fragment() {

    val viewModel: PlaceViewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }

    private lateinit var adapter: PlaceAdapter
    private lateinit var viewBinding: FragmentPlaceBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentPlaceBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is MainActivity && viewModel.isPlaceSaved()){
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
        initView()
    }

    private fun initView() {
        adapter = PlaceAdapter(this, viewModel.placeList)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        viewBinding.recyclerView.adapter = adapter
        viewBinding.searchPlaceEdit.addTextChangedListener{ editable ->
            val content = editable.toString()
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
                viewBinding.progress.visibility = View.VISIBLE
            }else{
                viewBinding.recyclerView.visibility = View.GONE
                viewBinding.bgImageView.visibility = View.VISIBLE
                viewBinding.progress.visibility = View.GONE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visibility = View.GONE
            val places = it.getOrNull()
            if (places != null){
                viewBinding.recyclerView.visibility = View.VISIBLE
                viewBinding.bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                "未能查询到任何地点".toast()
                it.exceptionOrNull()?.printStackTrace()
            }
        }
    }
}