package com.codewarsclient.ui.challenges.completed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.codewarsclient.R
import com.codewarsclient.ui.challenges.ChallengesFragment
import com.codewarsclient.ui.challenges.ChallengesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_challenges.*

@AndroidEntryPoint
class CompletedChallengesFragment : ChallengesFragment() {

    private val challengesViewModel: CompletedChallengesViewModel by viewModels()

    private val args: CompletedChallengesFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")

        // Set the member username
        challengesViewModel.setMemberUsername(args.memberUsername)

        // Search the challenges for the received member
        challengesViewModel.searchMemberChallenges()
    }

    override fun observeNavigationOptions() {
        bottom_nav_view.selectedItemId = R.id.navigation_challenges_completed

        bottom_nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_challenges_authored -> {
                    NavHostFragment.findNavController(this)
                        .navigate(
                            CompletedChallengesFragmentDirections.actionOpenChallengesAuthored(
                                challengesViewModel.getMemberUsername()
                            )
                        )
                    true
                }
                else -> false
            }
        }
    }

    override fun getViewModel(): ChallengesViewModel {
        return challengesViewModel
    }

    companion object {
        private val TAG: String = CompletedChallengesFragment::class.java.simpleName
    }
}