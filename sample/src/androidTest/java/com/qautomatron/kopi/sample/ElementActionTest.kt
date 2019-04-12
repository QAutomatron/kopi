package com.qautomatron.kopi.sample

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.qautomatron.kopi.library.element.Element
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
    fun should_get_text_action_when_use_action() {
        val text = element.getText()
        assertEquals("Hello World!", text)
    }
}
