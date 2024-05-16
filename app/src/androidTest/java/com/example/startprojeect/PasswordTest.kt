package com.example.startprojeect

import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.startprojeect.presentation.registration.SignInFragment
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PasswordTest {
    @JvmField
    @get:Rule
    var scenario = launchFragmentInContainer<SignInFragment>()

    @Test
    fun validationPassword(){
        val pass = "!Ab34567 8"
        onView(withId(R.id.password))
            .perform(typeText(pass), closeSoftKeyboard())
        val upper = pass.any{it.isUpperCase()}
        val under = pass.any { it.isLowerCase() }
        val digit = pass.any { it.isDigit() }
        val whiteSpace = pass.any { it.isWhitespace() }
        val checkSpecial = pass.any { it.isSpecialChar() }
        val isLenght = pass.length >= 8
        assert(upper && under && digit && whiteSpace && checkSpecial && isLenght)
    }
    private fun Char.isSpecialChar(): Boolean {
        val special = "!@#$%^*&*()_+-=.,/?"
        return this in special
    }
    @Test
    fun validationPasswordAndAlert() {
        val pass = "123!"
        onView(withId(R.id.password))
            .perform(typeText(pass), closeSoftKeyboard())
        val upper = pass.any { it.isUpperCase() }
        val under = pass.any { it.isLowerCase() }
        val space = pass.any { it.isWhitespace() }
        val digit = pass.any { it.isDigit() }
        val special = pass.any { it.isSpecialChar() }
        val isValidLenght = pass.length >= 8
        assert(!under || !upper || !space || !digit || !space || !isValidLenght || !special)
        onView(withId(R.id.hideTextView)).perform(click())
        onView(withText("Ошибка"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))

    }
}