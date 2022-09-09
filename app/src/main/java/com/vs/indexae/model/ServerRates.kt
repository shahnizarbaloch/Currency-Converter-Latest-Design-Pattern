package com.vs.indexae.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "server_rates")
data class ServerRates(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    var type: String,
    var amount: Double
){
    constructor() : this(0,"",0.0)
}