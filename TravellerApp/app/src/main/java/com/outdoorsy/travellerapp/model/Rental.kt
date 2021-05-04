package com.outdoorsy.travellerapp.model

import com.google.gson.annotations.SerializedName

class Rental : Item() {

    @SerializedName("attributes")
    private var attributes: Attributes? = null

    override fun getTitle(): String? {
        return attributes?.name
    }

    override fun getImageUrl(): String? {
        return attributes?.primaryImageUrl
    }
}