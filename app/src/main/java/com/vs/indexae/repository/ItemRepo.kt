package com.vs.indexae.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vs.indexae.dao.ItemDAO
import com.vs.indexae.model.ItemModel
import com.vs.indexae.model.ServerRates
import com.vs.indexae.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepo @Inject constructor (private val itemDAO: ItemDAO) {

    /**
     * method to get list of items from room database.
     */
    fun getItemList(): LiveData<MutableList<ServerRates>?>? {
        return itemDAO.listOfRates()
    }

    @WorkerThread
    suspend fun insert(serverRates: ServerRates) = withContext(Dispatchers.IO){
        itemDAO.insert(serverRates)
    }

    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var serverRatesMutableLiveData = MutableLiveData<ItemModel?>()

    /**
     * observe progress bar.
     */
    fun getIsLoading(): MutableLiveData<Boolean> {
        return isLoading
    }

    /**
     * get rate list.
     */
    fun getLatestRatesFromServer(){
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val response:Response<ItemModel> = RetrofitClient.instance!!.api.getLatestRates("df1514adea78b43dbc88fca62d7fac58")

            withContext(Dispatchers.IO){
                if (response.code() == 200){
                    isLoading.postValue(false)
                    serverRatesMutableLiveData.postValue(response.body())

                    //   loading.postValue(false)
                }/*else if (response.code() == 401){
                    // loading.postValue(false)
                    //(context.applicationContext as App).responseCodeMutableLiveData?.value=401

                }else{
                    //errorMessage.postValue(response.errorBody().toString())
                }*/
            }
        }

    }



    /**
     * method to return observe rate changes
     */
    fun observeRates() : LiveData<ItemModel?>{
        return serverRatesMutableLiveData
    }
}