package com.codewarsclient.ui.challengeDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewarsclient.repositories.ChallengeDetailsRepository
import kotlinx.coroutines.launch

class ChallengeDetailsViewModel @ViewModelInject constructor(
    private val challengeDetailsRepository: ChallengeDetailsRepository
) : ViewModel() {

    lateinit var challengeId: String

    private val _textTitle = MutableLiveData<String>().apply {
        value = ""
    }
    val textTitle: LiveData<String> = _textTitle

    private val _textRank = MutableLiveData<String>().apply {
        value = ""
    }
    val textRank: LiveData<String> = _textRank

    private val _textLanguages = MutableLiveData<String>().apply {
        value = ""
    }
    val textLanguages: LiveData<String> = _textLanguages

    private val _textTags = MutableLiveData<String>().apply {
        value = ""
    }
    val textTags: LiveData<String> = _textTags

    private val _textDescription = MutableLiveData<String>().apply {
        value = ""
    }
    val textDescription: LiveData<String> = _textDescription

    /**
     * Searches the details of a given challenge
     */
    fun searchMemberChallenges() {
        viewModelScope.launch {
            val challengeDetails = challengeDetailsRepository.getChallengeDetails(challengeId)

            _textTitle.value = challengeDetails.challengeName

            _textRank.value = challengeDetails.rankName

            _textLanguages.value = challengeDetails.languagesList.joinToString { it }

            _textTags.value = challengeDetails.tagsList.joinToString { it }

            _textDescription.value = challengeDetails.description
        }
    }
}