@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.not

/**
 * Element assertions
 */
interface ElementAssertions<T> {
    val element: ViewInteraction

    /**
     * Universal assertion method
     * @param assertion assertion
     */
    fun check(assertion: ViewAssertion): T {
        element.check(assertion)
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
     * Check element text
     * @param text expected text
     */
    fun sameAs(text: String) = check(matches(withText(text)))

    /**
     * Check element text
     * @param resourceId expected text by resourceId
     */
    fun sameAs(resourceId: Int) = check(matches(withText(resourceId)))
}