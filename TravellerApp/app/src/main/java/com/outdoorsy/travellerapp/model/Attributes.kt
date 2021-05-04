package com.outdoorsy.travellerapp.model

import com.google.gson.annotations.SerializedName

class Attributes {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("primary_image_url")
    var primaryImageUrl: String? = null
}
