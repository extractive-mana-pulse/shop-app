package com.example.effectivemobiletr.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class ImageSliderViewModel : ViewModel() {

    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> get() = _imageUrls

    // Method to update the image URLs
    fun setImageUrls(urls: List<String>) {
        _imageUrls.value = urls
    }
}