package com.codewarsclient.ui.challenges

import androidx.hilt.lifecycle.ViewModelInject
import com.codewarsclient.repositories.MemberRepository

class CompletedChallengesViewModel @ViewModelInject constructor(
    private val challengesRepository: MemberRepository
) : ChallengesViewModel() {

}