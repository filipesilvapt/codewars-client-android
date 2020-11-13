package com.codewarsclient

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MembersTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val clearDatabaseRule = ClearDatabaseRule()

    @Test
    fun assertMemberSearchHint() {
        onView(withId(R.id.input_text_member_name)).check(matches(withHint(R.string.hint_search_member)))
    }

    @Test
    fun assertMemberSearchedIsFound() {
        val memberUsername = "g964"

        onView(withId(R.id.input_text_member_name))
            .perform(click())
            .perform(typeText(memberUsername))
            .perform(pressImeActionButton())

        Thread.sleep(3000)

        assertDisplayedAtPosition(R.id.list_of_members, 0, R.id.text_name, memberUsername)
    }

    @Test
    fun assertMemberSortFunctions() {
        val memberUsernames = arrayOf("myjinxin2015", "qwe")

        memberUsernames.onEach {
            onView(withId(R.id.input_text_member_name))
                .perform(click())
                .perform(clearText())
                .perform(typeText(it))
                .perform(pressImeActionButton())
            Thread.sleep(3000)
        }

        // Asset sort by time of search desc
        assertDisplayedAtPosition(R.id.list_of_members, 0, R.id.text_name, memberUsernames[1])
        assertDisplayedAtPosition(R.id.list_of_members, 1, R.id.text_name, memberUsernames[0])

        // Press the sort by rank button
        clickOn(R.id.sort_by_rank)

        // Asset sort by rank desc
        assertDisplayedAtPosition(R.id.list_of_members, 0, R.id.text_name, memberUsernames[0])
        assertDisplayedAtPosition(R.id.list_of_members, 1, R.id.text_name, memberUsernames[1])
    }

}