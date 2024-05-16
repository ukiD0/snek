package com.example.startprojeect

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.startprojeect.presentation.registration.SignInFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
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
    fun validationPassword(){
        onView(withId(R.id.password)).perform(typeText("1233"), closeSoftKeyboard())
        //onView(withId(R.id.hideTextView)).perform(click())
//        onView(withText("Валидация успешна"))
//            .inRoot(isDialog())
//            .check(matches(isDisplayed()))
        onView(withId(R.id.password)).check { view, noViewFoundException ->
            if (view is EditText){
                assertEquals(view.text.toString().length > 6, true)
            }
        }

    }
    @Test
    fun validationPasswordAndAlert() {
        onView(withId(R.id.password)).perform(typeText("12345"), closeSoftKeyboard())
        onView(withId(R.id.password)).check { view, noViewFoundException ->
            if (view is EditText){
                assertEquals(view.text.toString().length < 6, true)
            }
        }
        onView(withId(R.id.hideTextView)).perform(click())
        onView(withText("Ошибка"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }
    @Test
    fun testInvalidFormatWithAlert(){
        val invalidEMail = "test@EEqq,ru"
        onView(withId(R.id.email))
            .perform(typeText(invalidEMail), closeSoftKeyboard())
        onView(withId(R.id.email)).check { textEdit, _ ->
            if (textEdit is EditText){
                val name = textEdit.text.toString().split("@")[0]
                val domain = textEdit.text.toString().split("@")[1].replace(".","")
                assert(name.all { !it.isDigit() || !it.isLowerCase() })
                assert(domain.all { !it.isDigit() || !it.isLowerCase() })
            }
        }
        onView(withId(R.id.emailValidation)).perform(click())
        onView(withText("Ошибка"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }
    @Test
    fun testValidEmailFormat() {
        val validEmail = "test@example1.com"
        onView(withId(R.id.email))
            .perform(typeText(validEmail), closeSoftKeyboard())
        onView(withId(R.id.email)).check{editText,_ ->
            if (editText is EditText){
                val emailSplit = editText.text.toString().split("@")
                val name = emailSplit[0]
                val domain = editText.text.toString().split("@")[1].replace(".","")
                assert(name.all { it.isLowerCase() || it.isDigit()})
                assert(domain.all { it.isDigit() || it.isLowerCase() })
            }
        }
    }

}