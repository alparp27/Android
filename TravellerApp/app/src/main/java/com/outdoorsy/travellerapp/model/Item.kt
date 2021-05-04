package com.outdoorsy.travellerapp.model

import com.google.gson.annotations.SerializedName

abstract class Item {

    @SerializedName("id")
    private var id: String? = null

    @SerializedName("type")
    private var type: String? = null

    abstract fun getTitle(): String?

    abstract fun getImageUrl(): String?
}
