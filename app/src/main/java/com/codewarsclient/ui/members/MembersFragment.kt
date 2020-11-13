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
import androidx.navigation.fragment.NavHostFragment
import com.codewarsclient.R
import com.codewarsclient.database.entities.MemberEntity
import com.codewarsclient.databinding.FragmentMembersBinding
import com.codewarsclient.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_members.*

@AndroidEntryPoint
class MembersFragment : Fragment() {

    private val membersViewModel: MembersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")

        val binding = FragmentMembersBinding.inflate(layoutInflater, container, false)

        binding.viewModel = membersViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")

        setupUI()

        observeSearchResults()

        membersViewModel.selectedMember.observe(viewLifecycleOwner,
            { member: MemberEntity ->
                NavHostFragment.findNavController(this)
                    .navigate(MembersFragmentDirections.actionOpenChallenges(member.username))
            })
    }

    private fun setupUI() {
        // Set action for end icon to search a member
        input_layout_member_name.setEndIconOnClickListener {
            input_text_member_name.clearFocus()
            input_text_member_name.hideKeyboard()
            searchMemberFromInputField()
        }

        // Set action for keyboard's done button to search a member
        input_text_member_name.setOnEditorActionListener { inputView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER))) {
                input_text_member_name.clearFocus()
                inputView.hideKeyboard()
                searchMemberFromInputField()
            }
            false
        }

        // Reset input layout state on focus
        input_text_member_name.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Clear error text
                input_layout_member_name.error = null
            }
        }

        // Setup sort menu actions
        bottom_sort_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.sort_by_search -> {
                    (list_of_members.adapter as MembersListAdapter).sortMembersList(
                        MembersSortOption.SEARCH_DESC
                    )
                    true
                }
                R.id.sort_by_rank -> {
                    (list_of_members.adapter as MembersListAdapter).sortMembersList(
                        MembersSortOption.RANK_DESC
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun searchMemberFromInputField() {
        membersViewModel.searchMemberByName(input_text_member_name.text.toString())
    }

    private fun observeSearchResults() {
        // Continuously observe changes to the search history to fill the list
        membersViewModel.listOfSearchedMembers.observe(viewLifecycleOwner,
            { members: List<MemberEntity> ->
                (list_of_members.adapter as MembersListAdapter).updateMembersList(members)
            })

        // Update error message accordingly
        membersViewModel.isToShowError.observe(viewLifecycleOwner,
            { isToShowError: Boolean ->
                if (isToShowError) {
                    // Set error text
                    input_layout_member_name.error = getString(R.string.error_search_member)
                } else {
                    // Clear error text
                    input_layout_member_name.error = null
                }
            })
    }

    companion object {
        private val TAG: String = MembersFragment::class.java.simpleName
    }
}