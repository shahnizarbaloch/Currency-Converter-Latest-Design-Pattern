package com.vs.indexae.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vs.indexae.model.ServerRates

@Dao
interface ItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(serverRate: ServerRates?)

    /*@Delete
    fun delete(serverRate: ServerRates?)*/

    //@Query("SELECT * FROM server_rates WHERE _id = :id")

    @Query("SELECT * FROM server_rates ORDER BY id ASC")
    fun listOfRates(): LiveData<MutableList<ServerRates>?>?

    /*@Query("SELECT * FROM server_rates WHERE id = :id")*/
}