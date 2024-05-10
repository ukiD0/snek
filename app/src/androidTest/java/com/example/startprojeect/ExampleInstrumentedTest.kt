package com.example.startprojeect

import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.startprojeect.presentation.registration.SignInFragment
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @JvmField
    @get:Rule
    val scenario = launchFragmentInContainer<SignInFragment>()

    @Test
    fun useAppContext() {
        onView(withId(R.id.password))
            .perform(typeText("12345"), closeSoftKeyboard())
        onView(withText("Ошибка"))
            .check(matches(isDisplayed()))
        onView(withId(R.id.password))
            .perform(replaceText("123456"), closeSoftKeyboard())
        onView(withText("Ошибка"))
            .check(matches(not(isDisplayed())))



//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.startprojeect", appContext.packageName)
    }
}