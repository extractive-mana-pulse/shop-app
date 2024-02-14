package com.example.effectivemobiletr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletr.model.Items
import com.example.effectivemobiletr.model.network.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api : Api) : ViewModel() {

    private val _items = MutableLiveData<List<Items>>()
    val items: LiveData<List<Items>> get() = _items

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val response = api.getAllItems()
                if (response.isSuccessful) {
                    val main = response.body()
                    val itemsList = main?.items
                    _items.value = itemsList!!
                } else {
                    // Handle error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}