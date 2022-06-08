@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import android.os.SystemClock
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.qautomatron.kopi.library.matcher.IsNotMovingMatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.not

/**
 * Element assertions
 */
interface ElementAssertions<T> {
    val element: ViewInteraction

    /**
     * Universal assertion method with retry
     * @param assertion assertion
     */
    fun check(assertion: ViewAssertion): T {
        var result: Boolean
        var exception: Throwable? = null
        val endTime = SystemClock.elapsedRealtime() + ElementAssertionConfig.timeout
        do {
            result = true
            element.withFailureHandler { error, _ ->
                if (error::class.java in ElementAssertionConfig.allowedExceptions) {
                    result = false
                    exception = error
                } else throw error
            }.check(assertion)
            if (!result) runBlocking { delay(ElementAssertionConfig.interval) }
        } while (SystemClock.elapsedRealtime() < endTime && !result)
        if (!result && exception != null) {
            throw exception as Throwable
        }
        return this as T
    }

    /**
     * Check that element is present in view
     */
    fun shouldBePresent() = check(matches(anyOf(isDisplayed(), not(isDisplayed()))))

    /**
     * Check that element is not exist
     */
    fun shouldNotBePresent() = check(doesNotExist())

    /**
     * Check that element is displayed in view
     * @param value percent of display
     */
    fun shouldBeDisplayed(value: Int = 100) = check(matches(isDisplayingAtLeast(value)))

    /**
     * Check that element is not displayed
     */
    fun shouldNotBeDisplayed() = check(matches(not(isCompletelyDisplayed())))

    /**
     * Check that element is enabled
     */
    fun shouldBeEnabled() = check(matches(isEnabled()))

    /**
     * Check that element is  not enabled
     */
    fun shouldNotBeEnabled() = check(matches(not(isEnabled())))

    /**
     * Check that element is checked
     */
    fun shouldBeChecked() = check(matches(isChecked()))

    /**
     * Check that element is not checked
     */
    fun shouldNotBeChecked() = check(matches(isNotChecked()))

    /**
     * Check that element is selected
     */
    fun shouldBeSelected() = check(matches(isSelected()))

    /**
     * Check that element is not selected
     */
    fun shouldNotBeSelected() = check(matches(not(isSelected())))

    /**
     * Check element text
     * @param text expected text
     */
    fun sameAs(text: String) = check(matches(withText(text)))

    /**
     * Check element text
     * @param resourceId expected text by resourceId
     */
    fun sameAs(resourceId: Int) = check(matches(withText(resourceId)))

    /**
     * Check element is not moving
     * @param timeoutInMills
     */
    fun isNotMoving(timeoutInMills: Int? = null) = check(matches(IsNotMovingMatcher(timeoutInMills)))
}