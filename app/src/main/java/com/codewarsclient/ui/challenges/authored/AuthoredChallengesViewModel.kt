package com.codewarsclient.ui.challenges.authored

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.codewarsclient.database.entities.AuthoredChallengeEntity
import com.codewarsclient.repositories.AuthoredChallengesRepository
import com.codewarsclient.ui.challenges.ChallengesListAdapter
import com.codewarsclient.ui.challenges.ChallengesViewModel
import kotlinx.coroutines.launch

class AuthoredChallengesViewModel @ViewModelInject constructor(
    private val challengesRepository: AuthoredChallengesRepository
) : ChallengesViewModel() {

    private val challengesListAdapter = AuthoredChallengesListAdapter()

    /**
     * Searches the member authored challenges to populate the list
     */
    fun searchMemberChallenges() {
        viewModelScope.launch {
            val completedChallengesWrapper =
                challengesRepository.getMemberAuthoredChallenges(getMemberUsername())

            challengesListAdapter.updateChallengesList(completedChallengesWrapper.challengesList)

            if (!completedChallengesWrapper.wasObtainedFromApi && completedChallengesWrapper.challengesList.isNotEmpty()) {
                setOfflineLoadStatus()
            }
        }
    }

    override fun getListAdapter(): ChallengesListAdapter<AuthoredChallengeEntity> {
        return challengesListAdapter
    }
}