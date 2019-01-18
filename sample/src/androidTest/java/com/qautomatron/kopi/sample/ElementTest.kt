package com.qautomatron.kopi.sample

import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.qautomatron.kopi.library.element.Element
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test to check Element methods
 */
@RunWith(AndroidJUnit4::class)
class ElementTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    private val element = Element(withId(R.id.message_text))

    @Test
    fun should_be_present() {
        element.shouldBePresent()
    }

    @Test
    fun should_be_visible() {
        element.shouldBeDisplayed()
    }

    @Test
    fun should_get_text() {
        val text = element.getText()
        assertEquals("Hello World!", text)
    }

}
