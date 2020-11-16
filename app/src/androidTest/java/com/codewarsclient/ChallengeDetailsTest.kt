package com.codewarsclient

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ChallengeDetailsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val clearDatabaseRule = ClearDatabaseRule()

    @Test
    fun assertChallengeDetails_Displayed() {
        val memberUsername = "wichu"
        val challengeName = "Array plus array"

        onView(withId(R.id.input_text_member_name))
            .perform(click())
            .perform(typeText(memberUsername))
            .perform(pressImeActionButton())

        // Wait for network call
        Thread.sleep(3000)

        clickListItem(R.id.list_of_members, 0)

        // Wait for network call
        Thread.sleep(3000)

        clickListItem(R.id.list_of_challenges, 0)

        // Wait for network call
        Thread.sleep(3000)

        // Check that the challenge name is correctly presented
        assertDisplayed(R.id.text_title, challengeName)
    }

}