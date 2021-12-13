package com.qautomatron.kopi.sample

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.qautomatron.kopi.library.element.Element
import org.junit.Test

/**
 * Wait for ElementWait
 */
class ElementWaitTest: BaseTest() {

    private val textElement = Element(withId(R.id.message_text))
    private val removeButton = Element(withId(R.id.button_remove_text))

    @Test
    fun should_wait_for_not_be_present() {
        removeButton.tap()
        // Element will be removed after couple of sec
        textElement.waitForNotPresent()
    }
}
