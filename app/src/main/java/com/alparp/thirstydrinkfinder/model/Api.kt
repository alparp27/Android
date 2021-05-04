package com.alparp.thirstydrinkfinder.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

        @GET("search.php")
        fun getRentals(@Query("s") query: String?): Call<Response<Drink>?>?
}