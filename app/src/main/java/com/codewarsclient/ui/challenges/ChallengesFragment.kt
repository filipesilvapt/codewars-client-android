package com.codewarsclient.ui.challenges

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codewarsclient.databinding.FragmentChallengesBinding
import kotlinx.android.synthetic.main.fragment_challenges.*

abstract class ChallengesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")

        val binding = FragmentChallengesBinding.inflate(layoutInflater, container, false)

        binding.viewModel = getViewModel()
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNavigationOptions()

        observeScrollActions()

        observeSelectedChallenge()
    }

    /**
     * Sets the navigation menu actions
     */
    abstract fun observeNavigationOptions()

    /**
     * Get the view model instantiated
     */
    abstract fun getViewModel(): ChallengesViewModel

    /**
     * Observes the actions on the scroll buttons
     */
    private fun observeScrollActions() {
        fab_list_top.setOnClickListener {
            getViewModel().getListAdapter().run {
                if (itemCount > 0) list_of_challenges.scrollToPosition(0)
            }
        }

        fab_list_bottom.setOnClickListener {
            getViewModel().getListAdapter().run {
                if (itemCount > 0) list_of_challenges.scrollToPosition(itemCount - 1)
            }
        }
    }

    /**
     * Observe the action of selecting a challenge
     */
    abstract fun observeSelectedChallenge()

    companion object {
        private val TAG: String = ChallengesFragment::class.java.simpleName
    }
}