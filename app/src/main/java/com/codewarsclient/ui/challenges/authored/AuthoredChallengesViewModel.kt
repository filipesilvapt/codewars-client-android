package com.codewarsclient.ui.challenges.authored

import androidx.hilt.lifecycle.ViewModelInject
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.repositories.CompletedChallengesRepository
import com.codewarsclient.ui.challenges.ChallengesViewModel

class AuthoredChallengesViewModel @ViewModelInject constructor(
    private val challengesRepository: CompletedChallengesRepository
) : ChallengesViewModel() {

    override fun getListAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        TODO("Not yet implemented")
    }
}