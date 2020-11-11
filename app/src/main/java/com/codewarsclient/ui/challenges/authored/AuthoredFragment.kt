package com.codewarsclient.ui.challenges.authored

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codewarsclient.R

class AuthoredFragment : Fragment() {

    private lateinit var authoredViewModel: AuthoredViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authoredViewModel =
            ViewModelProvider(this).get(AuthoredViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_challenges_authored, container, false)
        val textView: TextView = root.findViewById(R.id.text_title)
        authoredViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}