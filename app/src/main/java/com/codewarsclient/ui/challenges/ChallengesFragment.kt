package com.codewarsclient.ui.challenges

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codewarsclient.databinding.FragmentChallengesBinding

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
    }

    /**
     * Sets the navigation menu actions
     */
    abstract fun observeNavigationOptions()

    /**
     * Get the view model instantiated
     */
    abstract fun getViewModel(): ChallengesViewModel

    companion object {
        private val TAG: String = ChallengesFragment::class.java.simpleName
    }
}