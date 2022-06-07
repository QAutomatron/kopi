package com.qautomatron.kopi.sample

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.qautomatron.kopi.library.element.Element
import org.junit.Test

/**
 * Wait for
 */
class ElementWaitForTest: BaseTest() {

    private val textElement = Element(withId(R.id.message_text))

    @Test
    fun should_wait_for_displayed_with_timeout() {
        // Element will be removed after couple of sec
        textElement.waitFor(matches(isDisplayed()), timeoutInMillis = 10000)
    }
}
