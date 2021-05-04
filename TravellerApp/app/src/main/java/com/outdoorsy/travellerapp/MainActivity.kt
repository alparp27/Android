package com.outdoorsy.travellerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.outdoorsy.travellerapp.model.Api
import com.outdoorsy.travellerapp.model.Rental
import com.outdoorsy.travellerapp.model.Response
import com.outdoorsy.travellerapp.model.RestApiClient
import com.outdoorsy.travellerapp.ui.RecyclerAdapter
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    lateinit var searchBox: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    private var viewModel: SearchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBox = findViewById(R.id.filter)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerAdapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        viewModel = ViewModelProvider(viewModelStore, ViewModelProvider.NewInstanceFactory())
                .get(SearchViewModel::class.java)
        viewModel?.results?.observe(this, androidx.lifecycle.Observer  { rentals : Any? ->
            (rentals as List<Rental>)?.let {
                recyclerAdapter.setListItems(rentals)
            }
        })
        searchBox.addTextChangedListener {text ->
            viewModel?.filter(query = text.toString())
        }
    }

    override fun onPause() {
        viewModel?.cancel()
        super.onPause()
    }
}