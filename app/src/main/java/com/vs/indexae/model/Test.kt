package com.vs.indexae.model

data class Test(
    val base: String,
    val date: String,
    val rates: RatesX,
    val success: Boolean,
    val timestamp: Int
)