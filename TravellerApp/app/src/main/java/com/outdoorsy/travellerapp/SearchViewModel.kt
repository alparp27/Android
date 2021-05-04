package com.outdoorsy.travellerapp

import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.outdoorsy.travellerapp.model.Api
import com.outdoorsy.travellerapp.model.Rental
import com.outdoorsy.travellerapp.model.Response
import com.outdoorsy.travellerapp.model.RestApiClient
import retrofit2.Call
import retrofit2.Callback

class SearchViewModel : ViewModel() {

    private var request: Call<Response<Rental>?>? = null

    val results: MutableLiveData<List<Rental>> by lazy {
        MutableLiveData<List<Rental>>()
    }

    fun filter(query: String?) {
        val apiInterface = RestApiClient().getClient()?.create(Api::class.java)
        apiInterface?.let {
           cancel()
            request = it.getRentals(query.toString(), null, null)
            request?.enqueue(object : Callback<Response<Rental>?> {
                override fun onResponse(
                        call: Call<Response<Rental>?>,
                        response: retrofit2.Response<Response<Rental>?>
                ) {
                    Log.d("MainActivity", "onResponse")
                    response?.body()?.data?.let { rentals ->
                        results.postValue(rentals)
                    }
                }

                override fun onFailure(call: Call<Response<Rental>?>, t: Throwable) {
                    Log.d("MainActivity", "onFailure")
                    //TODO: handle error/show offline searches
                }
            })
        }
    }

    fun cancel() {
        request?.cancel()
    }
}