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

    fun searchMemberChallenges() {
        viewModelScope.launch {
            val challengesModel =
                challengesRepository.getMemberAuthoredChallenges(getMemberUsername())

            challengesModel?.let {
                challengesListAdapter.updateChallengesList(it.challengesList)
            }
        }
    }

    override fun getListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return challengesListAdapter
    }
}