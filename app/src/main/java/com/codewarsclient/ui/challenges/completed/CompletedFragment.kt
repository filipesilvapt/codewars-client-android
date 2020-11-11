package com.codewarsclient.ui.challenges.completed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codewarsclient.R

class CompletedFragment : Fragment() {

    private lateinit var completedViewModel: CompletedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        completedViewModel =
            ViewModelProvider(this).get(CompletedViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_challenges_completed, container, false)
        val textView: TextView = root.findViewById(R.id.text_title)
        completedViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}