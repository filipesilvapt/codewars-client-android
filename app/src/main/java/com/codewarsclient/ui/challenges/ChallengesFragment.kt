package com.codewarsclient.ui.challenges

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.codewarsclient.R
import com.codewarsclient.ui.members.MembersFragment
import com.codewarsclient.ui.members.MembersViewModel
import kotlinx.android.synthetic.main.fragment_challenges.*

class ChallengesFragment : Fragment() {

    private val challengesViewModel: ChallengesViewModel by viewModels()

    private val args: ChallengesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")

        return inflater.inflate(R.layout.fragment_challenges, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")

        text_title.text = args.memberUsername
    }

    companion object {
        private val TAG: String = MembersFragment::class.java.simpleName
    }
}