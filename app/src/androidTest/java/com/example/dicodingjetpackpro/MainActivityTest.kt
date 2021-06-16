package com.example.dicodingjetpackpro

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.dicodingjetpackpro.ui.MainActivity
import com.example.dicodingjetpackpro.utils.EspressoIdlingResource
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    private val position = 0

    @Test
    fun fullTest() {
        onView(
            Matchers.allOf(
                withId(R.id.rvListGeneral),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        )
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
            )
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBack)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop)).perform(click())
        onView(withId(R.id.fabBookmark)).perform(click())
        onView(withId(R.id.btnBack)).perform(click())

        onView(
            Matchers.allOf(
                withId(R.id.tabLayout),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        )
            .check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(
            Matchers.allOf(
                withId(R.id.rvListGeneral),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBack)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop)).perform(click())
        onView(withId(R.id.fabBookmark)).perform(click())
        onView(withId(R.id.btnBack)).perform(click())

        onView(
            Matchers.allOf(
                withId(R.id.fabFav),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        )
            .check(matches(isDisplayed()))
            .perform(click())

        onView(
            Matchers.allOf(
                withId(R.id.rvFav),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBack)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop)).perform(click())
        onView(withId(R.id.fabBookmark)).perform(click())
        onView(withId(R.id.btnBack)).perform(click())
        onView(
            Matchers.allOf(
                withId(R.id.tabLayout),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        )
            .check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).perform(click())
        onView(
            Matchers.allOf(
                withId(R.id.rvFav),
                withEffectiveVisibility(Visibility.VISIBLE),
                isCompletelyDisplayed()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        onView(withId(R.id.titleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.date)).check(matches(isDisplayed()))
        onView(withId(R.id.runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.score)).check(matches(isDisplayed()))
        onView(withId(R.id.btnBack)).check(matches(isDisplayed()))
        onView(withId(R.id.fabBookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop)).perform(click())
        onView(withId(R.id.fabBookmark)).perform(click())
        onView(withId(R.id.btnBack)).perform(click())
    }
}