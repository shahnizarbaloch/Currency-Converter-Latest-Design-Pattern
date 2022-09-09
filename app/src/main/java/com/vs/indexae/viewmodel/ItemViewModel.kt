package com.vs.indexae.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs.indexae.model.ItemModel
import com.vs.indexae.model.ServerRates
import com.vs.indexae.repository.ItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ItemViewModel @Inject constructor(
    private var itemRepo: ItemRepo
) : ViewModel() {

    fun insert(serverRates: ServerRates) {
        viewModelScope.launch {
            itemRepo.insert(serverRates)
        }
    }

    fun getListOffline(): LiveData<MutableList<ServerRates>?>? {
        return itemRepo.getItemList()
    }

    fun getIsLoading(): LiveData<Boolean> {
        return itemRepo.getIsLoading()
    }

    fun getLatestRateFromServer(){
        itemRepo.getLatestRatesFromServer()
    }

    fun observeBundleCounter(): LiveData<ItemModel?> {
        return itemRepo.observeRates()
    }

}