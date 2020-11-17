package com.codewarsclient.ui.challengeDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewarsclient.R
import com.codewarsclient.repositories.ChallengeDetailsRepository
import com.codewarsclient.utils.Utils
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

    private val _textStatusMessageResId = MutableLiveData<Int>().apply {
        value = null
    }
    val textStatusMessageResId: LiveData<Int> = _textStatusMessageResId

    /**
     * Searches the details of a given challenge
     */
    fun searchMemberChallenges() {
        viewModelScope.launch {
            val challengeDetailsWrapper =
                challengeDetailsRepository.getChallengeDetails(challengeId)

            challengeDetailsWrapper.challengesDetailsEntity?.let { entity ->
                _textTitle.value = entity.challengeName

                _textRank.value = entity.rankName

                _textLanguages.value = Utils.capitalizeAndJoinLanguagesList(entity.languagesList)

                _textTags.value = entity.tagsList.joinToString { it }

                _textDescription.value = entity.description

                if (!challengeDetailsWrapper.wasObtainedFromApi) {
                    _textStatusMessageResId.value = R.string.status_challenge_details_offline_load
                }
            } ?: run {
                _textStatusMessageResId.value = R.string.status_challenge_details_not_found
            }
        }
    }
}