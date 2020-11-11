package com.codewarsclient.ui.challengeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codewarsclient.R

class ChallengeDetailsFragment : Fragment() {

    private lateinit var challengeDetailsViewModel: ChallengeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        challengeDetailsViewModel =
            ViewModelProvider(this).get(ChallengeDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_challenge_details, container, false)
        val textView: TextView = root.findViewById(R.id.text_title)
        challengeDetailsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}