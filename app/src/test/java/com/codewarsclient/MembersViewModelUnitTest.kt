package com.codewarsclient

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codewarsclient.api.ApiClient
import com.codewarsclient.api.ApiService
import com.codewarsclient.repositories.FakeMemberRepository
import com.codewarsclient.ui.members.MembersViewModel
import com.codewarsclient.utils.ApiDispatcher
import com.codewarsclient.utils.CoroutinesTestRule
import com.codewarsclient.utils.LogTestRule
import com.codewarsclient.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class MembersViewModelUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    var logTestRule = LogTestRule()

    private lateinit var membersViewModel: MembersViewModel

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        mockWebServer.dispatcher = ApiDispatcher()

        membersViewModel = MembersViewModel(
            FakeMemberRepository(
                ApiClient(mockWebServer.url("/")).getInstance().create(ApiService::class.java)
            )
        )
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

        sleep(300)

        val membersSearched = membersViewModel.listOfSearchedMembers.getOrAwaitValue()

        assertThat(membersSearched.size).isEqualTo(1)
    }

    @Test
    fun `searching a member name that does not exist, results in error message`() {
        membersViewModel.usernameToSearch.value = "MemberDoesNotExist"

        membersViewModel.searchMemberFromInputField()

        sleep(300)

        val resourceId = membersViewModel.searchErrorTextResId.getOrAwaitValue()

        assertThat(resourceId).isEqualTo(R.string.error_search_member_not_found)
    }

    @Test
    fun `searching a member name without internet connection, results in warning message`() {
        membersViewModel.usernameToSearch.value = "g964"

        membersViewModel.searchMemberFromInputField()

        sleep(300)

        val resourceId = membersViewModel.searchHelperTextResId.getOrAwaitValue()

        assertThat(resourceId).isEqualTo(R.string.message_search_member_not_found_offline)
    }

    @Test
    fun `searching a member repeatedly, results in bringing it to the top of the list`() {
        val memberUsernames = arrayOf("wichu", "AlexIsHappy")

        // Search the first member
        membersViewModel.usernameToSearch.value = memberUsernames[0]

        membersViewModel.searchMemberFromInputField()

        sleep(300)

        var membersSearched = membersViewModel.listOfSearchedMembers.getOrAwaitValue()

        // Check that the first member is in the first position of the searched members list
        assertThat(memberUsernames[0]).isEqualTo(membersSearched[0].username)

        // Check that the list contains 1 member
        assertThat(membersSearched.size).isEqualTo(1)

        // Search the second member
        membersViewModel.usernameToSearch.value = memberUsernames[1]

        membersViewModel.searchMemberFromInputField()

        sleep(300)

        membersSearched = membersViewModel.listOfSearchedMembers.getOrAwaitValue()

        // Check that the second member is in the first position of the searched members list
        assertThat(memberUsernames[1]).isEqualTo(membersSearched[0].username)

        // Check that the list contains 2 members
        assertThat(membersSearched.size).isEqualTo(2)

        // Search the first member again
        membersViewModel.usernameToSearch.value = memberUsernames[0]

        membersViewModel.searchMemberFromInputField()

        sleep(300)

        membersSearched = membersViewModel.listOfSearchedMembers.getOrAwaitValue()

        // Check that the first member is back to the first position of the searched members list
        assertThat(membersSearched[0].username).isEqualTo(memberUsernames[0])

        // Check that the list still contains 2 members
        assertThat(membersSearched.size).isEqualTo(2)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}