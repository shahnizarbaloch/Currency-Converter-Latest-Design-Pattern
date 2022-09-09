package com.vs.indexae.model

import com.google.gson.annotations.SerializedName


data class ItemModel(

    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("timestamp") var timestamp: Int? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("rates") var rates: Rates? = Rates()

)