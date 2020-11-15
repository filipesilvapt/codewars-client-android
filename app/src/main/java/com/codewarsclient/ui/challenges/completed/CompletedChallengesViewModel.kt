package com.codewarsclient.ui.challenges.completed

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.repositories.CompletedChallengesRepository
import com.codewarsclient.ui.challenges.ChallengesViewModel
import kotlinx.coroutines.launch

class CompletedChallengesViewModel @ViewModelInject constructor(
    private val challengesRepository: CompletedChallengesRepository
) : ChallengesViewModel() {

    private val challengesListAdapter = CompletedChallengesListAdapter()

    fun searchMemberChallenges() {
        viewModelScope.launch {
            val challengesList =
                challengesRepository.getMemberCompletedChallenges(getMemberUsername(), 0)

            challengesList?.let {
                challengesListAdapter.appendToChallengesList(challengesList)
            }
        }
    }

    override fun getListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return challengesListAdapter
    }
}