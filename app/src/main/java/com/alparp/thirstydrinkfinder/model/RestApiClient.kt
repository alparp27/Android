package com.alparp.thirstydrinkfinder.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestApiClient {

    var BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}