package com.codewarsclient.ui.members

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewarsclient.R
import com.codewarsclient.models.MemberModel
import com.codewarsclient.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_members.*

class MembersFragment : Fragment() {

    private val membersViewModel: MembersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")

        return inflater.inflate(R.layout.fragment_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated - Starting observers")

        // Configure recycler view
        list_of_members.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MembersListAdapter()
        }

        // Set action for end icon to search a member
        input_layout_member_name.setEndIconOnClickListener {
            input_text_member_name.hideKeyboard()
            searchMemberFromInputField()
        }

        // Set action for keyboard's done button to search a member
        input_text_member_name.setOnEditorActionListener { inputView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchMemberFromInputField()
            } else if (keyEvent != null && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) {
                inputView.hideKeyboard()
                searchMemberFromInputField()
            }
            false
        }
    }

    private fun searchMemberFromInputField() {
        membersViewModel.searchMemberByName(input_text_member_name.text.toString())
            .observe(viewLifecycleOwner,
                { memberFound: MemberModel ->
                    (list_of_members.adapter as MembersListAdapter).addItem(memberFound)
                })
    }

    companion object {
        private val TAG: String = MembersFragment::class.java.simpleName
    }
}