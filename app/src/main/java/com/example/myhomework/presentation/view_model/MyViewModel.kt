package com.example.myhomework.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel() : ViewModel() {

    val liveData = MutableLiveData<String>("First string")

    fun updateTextField() {
        liveData.value = "Second string"
    }



}