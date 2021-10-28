package com.example.commentsapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CommentsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun commentsActivityTest() {
        Thread.sleep(1000)
        val commentButtonPress = onView(withId(R.id.commentButton))
        commentButtonPress.perform(click())

        val appCompatEditText = onView(withId(R.id.messageEditText))
        appCompatEditText.perform(replaceText("Hello World"), closeSoftKeyboard())

        val appCompatImageView = onView(withId(R.id.sendButton))
        appCompatImageView.perform(click())

        Thread.sleep(500)
        onView(withId(R.id.messageTextView)).check(ViewAssertions.matches(isDisplayed()))
    }
}
