package com.codewarsclient.ui.challenges

import androidx.hilt.lifecycle.ViewModelInject
import com.codewarsclient.repositories.MemberRepository

class AuthoredChallengesViewModel @ViewModelInject constructor(
    private val challengesRepository: MemberRepository
) : ChallengesViewModel() {

}