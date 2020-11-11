package com.codewarsclient.ui.challenges.authored

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthoredViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is authored challenges Fragment"
    }
    val text: LiveData<String> = _text
}