package com.codewarsclient.ui.challenges.authored

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.repositories.AuthoredChallengesRepository
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

    override fun getListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return challengesListAdapter
    }
}