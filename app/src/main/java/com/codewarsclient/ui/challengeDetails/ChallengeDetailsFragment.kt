package com.codewarsclient.ui.challengeDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.codewarsclient.databinding.FragmentChallengeDetailsBinding

class ChallengeDetailsFragment : Fragment() {

    private val challengeDetailsViewModel: ChallengeDetailsViewModel by viewModels()

    private val args: ChallengeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")

        val binding = FragmentChallengeDetailsBinding.inflate(layoutInflater, container, false)

        binding.viewModel = challengeDetailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")

        // Set the challenge id
        challengeDetailsViewModel.challengeId = args.challengeId

        // Search the challenges for the received member
        challengeDetailsViewModel.searchMemberChallenges()
    }

    companion object {
        private val TAG: String = ChallengeDetailsFragment::class.java.simpleName
    }
}