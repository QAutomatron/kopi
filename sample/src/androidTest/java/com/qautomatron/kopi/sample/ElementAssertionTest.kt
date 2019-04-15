package com.qautomatron.kopi.sample

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.qautomatron.kopi.library.element.Element
import com.qautomatron.kopi.library.resourceToString
import org.junit.Rule
import org.junit.Test

/**
 * Check for ElementAssertion
 */
class ElementAssertionTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun should_be_present() {
        val element = Element(withId(R.id.message_text))
        element.shouldBePresent()
    }

    @Test
    fun should_be_visible() {
        val element = Element(withId(R.id.message_text))
        element.shouldBeDisplayed()
    }

    @Test
    fun should_be_same_as_text_resource() {
        val element = Element(withId(R.id.message_text))
        element.sameAs(R.string.text_hello)
    }

    @Test
    fun should_be_same_as_text() {
        val element = Element(withId(R.id.message_text))
        element.sameAs(resourceToString(R.string.text_hello))
    }

    @Test
    fun should_be_enabled() {
        val button = Element(withId(R.id.button_remove_text))
        button.shouldBeEnabled()
    }

    @Test
    fun should_not_be_enabled() {
        val button = Element(withId(R.id.button_disabled))
        button.shouldNotBeEnabled()
    }

    @Test
    fun should_not_be_checked() {
        val checkbox = Element(withId(R.id.checkbox_empty))
        checkbox.shouldNotBeChecked()
    }

    @Test
    fun should_be_checked() {
        val checkbox = Element(withId(R.id.checkbox_mark))
        checkbox.shouldBeChecked()
    }

    @Test
    fun should_not_be_visible() {
        val button =  Element(withId(R.id.button_hide))
        button.tap()
        button.shouldNotBeDisplayed()
    }
}
