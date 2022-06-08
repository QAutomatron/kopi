package com.qautomatron.kopi.sample

import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.qautomatron.kopi.library.element.Element
import com.qautomatron.kopi.library.matcher.IsNotMovingMatcher
import com.qautomatron.kopi.library.matcher.first
import org.junit.Test

/**
 * Check for ElementAction
 */
class ElementMatcherTest: BaseTest() {

    @Test
    fun should_get_first_element() {
        val element = Element(first(withId(R.id.sameId1)))
        element.sameAs("Text1")
    }

    @Test
    fun should_is_not_moving_matcher() {
        val element = Element(withId(R.id.message_text))
        val timeoutInMills = 1000
        element.isNotMoving(timeoutInMills)
    }
}
