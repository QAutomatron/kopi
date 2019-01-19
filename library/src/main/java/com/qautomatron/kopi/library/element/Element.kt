package com.qautomatron.kopi.library.element

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Root
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.uiautomator.Direction
import android.view.View
import com.qautomatron.kopi.library.element.action.GetTextAction
import com.qautomatron.kopi.library.element.action.nestedScrollTo
import com.qautomatron.kopi.library.wait.ViewMatcherWaiter
import org.hamcrest.Matcher
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.not

/**
 * Wrapper for onView with actions
 */
class Element(private val matcher: Matcher<View>, private val root: Matcher<Root>? = null) {

    val element: ViewInteraction
        get() {
            return if (root != null) {
                onView(matcher).inRoot(root)
            } else {
                onView(matcher)
            }
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
    fun shouldBeChecked() = waitForPresent().check(matches(isChecked()))

    /**
     * Check that element is not checked
     */
    fun shouldNotBeChecked() = waitForPresent().check(matches(isNotChecked()))

    /**
     * Check element text
     * @param text expected text
     */
    fun sameAs(text: String) = waitForPresent().check(matches(withText(text)))

    /**
     * Check element text
     * @param resourceId expected text by resourceId
     */
    fun sameAs(resourceId: Int) = waitForPresent().check(matches(withText(resourceId)))

    /**
     * Universal assertion method
     * @param assertion assertion
     */
    fun check(assertion: ViewAssertion): Element {
        element.check(assertion)
        return this
    }

    /**
     * Perform action and return element
     * @param action action to perform
     */
    fun perform(vararg action: ViewAction): Element {
        action.forEach { element.perform(it) }
        return this
    }

    /**
     * Type text into element
     * @param text text to type
     * @param clear clear field before type (true/false)
     * @param hideKeyboard hide keyboard after type (true/false)
     */
    fun type(text: String, clear: Boolean = false, hideKeyboard: Boolean = false) {
        if (clear) clear()
        perform(typeText(text))
        if (hideKeyboard) closeSoftKeyboard()
    }

    /**
     * Replace text in element
     */
    fun replace(text: String) = perform(replaceText(text))

    /**
     * Tap on element
     */
    fun tap() = perform(click())

    /**
     * Double tap on element
     */
    fun doubleTap() = perform(doubleClick())

    /**
     * Long tap on element
     */
    fun longTap() = perform(longClick())

    /**
     * Scroll to element (scrollview)
     * @param nested is element in nestedview (true/false)
     */
    fun scroll(nested: Boolean = false): Element {
        if (nested) {
            perform(nestedScrollTo())
        } else {
            perform(scrollTo())
        }
        return this
    }

    /**
     * Get text of element
     */
    fun getText(): String {
        val action = GetTextAction()
        perform(action)
        return action.text.toString()
    }

    /**
     * Clear text in element
     */
    fun clear() = perform(clearText())

    /**
     * Wait for element visibility
     * @param percent percent of visibility (90 by default)
     */
    fun waitForVisibility(percent: Int = 90): Element {
        waitFor(isDisplayingAtLeast(percent))
        return this
    }

    /**
     * Wait for element invisibility
     */
    fun waitForInvisibility(): Element {
        waitFor(not(isDisplayed()))
        return this
    }

    /**
     * Wait for element present
     */
    fun waitForPresent(): Element {
        waitFor(anyOf(isDisplayed(), not(isDisplayed())))
        return this
    }

    /**
     * Swipe on element
     * @param direction direction of swipe
     */
    fun swipe(direction: Direction) {
        when (direction) {
            Direction.LEFT -> perform(swipeLeft())
            Direction.RIGHT -> perform(swipeRight())
            Direction.UP -> perform(swipeUp())
            Direction.DOWN -> perform(swipeDown())
        }
    }

    /**
     * Wait for specific matcher
     */
    fun waitFor(matcher: Matcher<View>): Element {
        ViewMatcherWaiter(this.matcher).toMatch(matcher, this.root)
        return this
    }

    /**
     * Invoke element function
     */
    operator fun invoke(function: Element.() -> Unit) = this.function()
}