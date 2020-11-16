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

    private var pageNumber = 0

    /**
     * Searches the member completed challenges using the pagination system from the api for a lazy
     * loading approach when populating the list
     */
    fun searchMemberChallenges() {
        viewModelScope.launch {
            val completedChallengesWrapper =
                challengesListAdapter.getLastChallengeCompletedAt()?.let {
                    challengesRepository.getMemberCompletedChallenges(
                        getMemberUsername(),
                        pageNumber,
                        it
                    )
                } ?: run {
                    challengesRepository.getMemberCompletedChallenges(
                        getMemberUsername(),
                        pageNumber
                    )
                }

            // Update the page number in case it increased during the search
            pageNumber = completedChallengesWrapper.wasObtainedAtPage

            if (completedChallengesWrapper.challengesList.isNotEmpty()) {
                // If the challenges were obtained from the api, set the page number to the next one
                // Otherwise, keep it the same in order to be retried in the next load more request
                if (completedChallengesWrapper.wasObtainedFromApi) pageNumber++

                challengesListAdapter.appendToChallengesList(completedChallengesWrapper.challengesList)
            } else {
                setEndOfListReachedStatus()
            }
        }
    }

    fun getLastItemPosition(): Int {
        return challengesListAdapter.itemCount - 1
    }

    override fun getListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return challengesListAdapter
    }
}