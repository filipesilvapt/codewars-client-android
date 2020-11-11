package com.codewarsclient.ui.challengeDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChallengeDetailsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is challenge details Fragment"
    }
    val text: LiveData<String> = _text
}