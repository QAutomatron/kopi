package com.qautomatron.kopi.sample

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.qautomatron.kopi.library.element.Element
import com.qautomatron.kopi.library.resourceToString
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Check for ElementAction
 */
class ElementActionTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val element = Element(withId(R.id.message_text))

    @Test
    fun test() {
        val button = Element(withId(R.id.button_hide))
        button.tap()

        button.tap()
    }

    @Test
    fun should_get_text_action_when_use_action() {
        val text = element.getText()
        assertEquals(resourceToString(R.string.text_hello), text)
    }

    @Test
    fun should_clear_text_field_when_true() {
        val editText = Element(withId(R.id.editText))
        val expectedText = "Test"

        editText.type(expectedText)
        editText.type(expectedText, clear = true)
        editText.sameAs(expectedText)
    }

    @Test
    fun should_not_clear_text_field_by_default() {
        val editText = Element(withId(R.id.editText))
        val expectedText = "Test"

        editText.type(expectedText)
        editText.type(expectedText)
        editText.sameAs("$expectedText$expectedText")
    }

    @Test
    fun should_replace_text() {
        val editText = Element(withId(R.id.editText))
        val expectedText = "Test"
        val replacedText = "Replaced"

        editText.type(expectedText)
        editText.replace(replacedText)
        editText.sameAs(replacedText)
    }
}
