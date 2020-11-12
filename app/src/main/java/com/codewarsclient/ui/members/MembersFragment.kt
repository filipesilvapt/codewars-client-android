package com.codewarsclient.ui.members

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewarsclient.R
import com.codewarsclient.models.MemberModel

class MembersFragment : Fragment() {

    private val membersViewModel: MembersViewModel by viewModels()

    private lateinit var listOfMembers: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")

        val root = inflater.inflate(R.layout.fragment_members, container, false)

        listOfMembers = root.findViewById(R.id.list_of_members)

        listOfMembers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MembersListAdapter()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated - Starting observers")

        membersViewModel.searchMemberByName("AlexIsHappy").observe(viewLifecycleOwner,
            { memberFound: MemberModel ->
                (listOfMembers.adapter as MembersListAdapter).addItem(memberFound)
            })
    }

    companion object {
        private val TAG: String = MembersFragment::class.java.simpleName
    }
}