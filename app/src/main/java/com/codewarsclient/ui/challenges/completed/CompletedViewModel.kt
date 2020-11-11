package com.codewarsclient.ui.challenges.completed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompletedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is completed challenges Fragment"
    }
    val text: LiveData<String> = _text
}