package com.codewarsclient.ui.challenges.completed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        observeListScrollAction()
    }

    override fun observeNavigationOptions() {
        // Set the correct menu item as selected
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

    /**
     * Observes actions related to the scrolling of the list of challenges in order to load more
     * items and to update the end of list status
     */
    private fun observeListScrollAction() {
        list_of_challenges.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Get a normalised position where the end of list status should be cleared
                val positionToClearEndOfListStatus =
                    (challengesViewModel.getLastItemPosition() - 2).let {
                        if (it < 0) 0
                        else it
                    }

                // Clear the end of list status when the list is scrolled a few items up
                if ((list_of_challenges.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() < positionToClearEndOfListStatus) {
                    challengesViewModel.clearStatusMessage()
                }

                // Load more challenges when the user scrolls to the bottom of the list unless the end of the list has been reached
                if ((list_of_challenges.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == challengesViewModel.getLastItemPosition()) {
                    if (challengesViewModel.endOfListReached) {
                        challengesViewModel.setEndOfListReachedStatus()
                    } else {
                        challengesViewModel.searchMemberChallenges()
                    }
                }
            }
        })
    }

    companion object {
        private val TAG: String = CompletedChallengesFragment::class.java.simpleName
    }
}