package com.codewarsclient

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codewarsclient.repositories.FakeMemberRepository
import com.codewarsclient.ui.members.MembersViewModel
import com.codewarsclient.utils.CoroutinesTestRule
import com.codewarsclient.utils.LogTestRule
import com.codewarsclient.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MembersViewModelUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var logTestRule = LogTestRule()

    private lateinit var membersViewModel: MembersViewModel

    @Before
    fun setUp() {
        membersViewModel = MembersViewModel(FakeMemberRepository())
    }

    @Test
    fun `searching a member name containing only spaces, results in error message`() {
        membersViewModel.usernameToSearch.value = "          "

        membersViewModel.searchMemberFromInputField()

        val resourceId = membersViewModel.searchErrorTextResId.getOrAwaitValue()

        assertThat(resourceId).isEqualTo(R.string.error_search_member_empty)
    }

    @Test
    fun `searching a member name, results in a new entry in the members list`() {
        membersViewModel.usernameToSearch.value = "wichu"

        membersViewModel.searchMemberFromInputField()

        val membersSearched = membersViewModel.listOfSearchedMembers.getOrAwaitValue()

        assertThat(membersSearched.size).isEqualTo(1)
    }
}