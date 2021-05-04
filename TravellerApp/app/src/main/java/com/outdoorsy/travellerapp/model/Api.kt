package com.outdoorsy.travellerapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

        @GET("/rentals")
        fun getRentals(@Query("filter[keywords]") keywords: String?,
                       @Query("limit") pageLimit: Int?,
                       @Query("offset") pageOffset: Int?): Call<Response<Rental>?>?
}