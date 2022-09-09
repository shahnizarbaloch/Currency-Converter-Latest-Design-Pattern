package com.vs.indexae.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vs.indexae.dao.ItemDAO
import com.vs.indexae.model.ServerRates

@Database(entities = [ServerRates::class], exportSchema = false, version = 1)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDAO

}