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

        observeMemberSearchAction()

        observeSortMenuActions()

        observeSearchHistory()

        observeSelectedMember()
    }

    /**
     * Sets the action for the search field end icon and the keyboard action done button
     */
    private fun observeMemberSearchAction() {
        // Set action for end icon to search a member
        input_layout_member_name.setEndIconOnClickListener {
            input_text_member_name.clearFocus()
            input_text_member_name.hideKeyboard()
            membersViewModel.searchMemberFromInputField()
        }

        // Set action for keyboard's done button to search a member
        input_text_member_name.setOnEditorActionListener { inputView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE || (keyEvent != null && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER))) {
                inputView.clearFocus()
                inputView.hideKeyboard()
                membersViewModel.searchMemberFromInputField()
            }
            false
        }
    }

    /**
     * Sets the sort menu actions
     */
    private fun observeSortMenuActions() {
        bottom_sort_menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.sort_by_search -> {
                    membersViewModel.sortMembersList(MembersSortOption.SEARCH_DESC)
                    true
                }
                R.id.sort_by_rank -> {
                    membersViewModel.sortMembersList(MembersSortOption.RANK_DESC)
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Continuously observes changes to the search history to keep the list updated
     */
    private fun observeSearchHistory() {
        membersViewModel.listOfSearchedMembers.observe(viewLifecycleOwner,
            { members: List<MemberEntity> ->
                (list_of_members.adapter as MembersListAdapter).updateMembersList(members)
            })
    }

    /**
     * Observes member selection to navigate to the challenges page
     */
    private fun observeSelectedMember() {
        membersViewModel.selectedMember.observe(viewLifecycleOwner,
            { member: MemberEntity? ->
                member?.let {
                    NavHostFragment.findNavController(this)
                        .navigate(MembersFragmentDirections.actionOpenChallenges(member.username))
                }
            })
    }

    companion object {
        private val TAG: String = MembersFragment::class.java.simpleName
    }
}