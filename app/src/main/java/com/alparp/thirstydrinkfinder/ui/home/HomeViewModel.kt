package com.alparp.thirstydrinkfinder.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alparp.thirstydrinkfinder.model.*
import retrofit2.Call
import retrofit2.Callback

class HomeViewModel : ViewModel() {

    private var request: Call<Response<Drink>?>? = null

    val results: MutableLiveData<List<Drink>> by lazy {
        MutableLiveData<List<Drink>>()
    }

    fun search(query: String) {
        val apiInterface = RestApiClient().getClient()?.create(Api::class.java)
        apiInterface?.let {
            cancel()
            request = it.getRentals(query.toString())
            request?.enqueue(object : Callback<Response<Drink>?> {
                override fun onResponse(
                    call: Call<Response<Drink>?>,
                    response: retrofit2.Response<Response<Drink>?>
                ) {
                    Log.d("HomeViewModel", "onResponse")
                    response?.body()?.drinks?.let { drinks ->
                        results.postValue(drinks)
                    }
                }

                override fun onFailure(call: Call<Response<Drink>?>, t: Throwable) {
                    Log.d("HomeViewModel", "onFailure:"+t.toString())
                    //TODO: handle error/show offline searches
                }
            })
        }
    }

    fun cancel() {
        request?.cancel()
    }
}