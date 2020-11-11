package com.codewarsclient.ui.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codewarsclient.R

class MembersFragment : Fragment() {

    private lateinit var membersViewModel: MembersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        membersViewModel =
            ViewModelProvider(this).get(MembersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_members, container, false)
        val textView: TextView = root.findViewById(R.id.text_title)
        membersViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}