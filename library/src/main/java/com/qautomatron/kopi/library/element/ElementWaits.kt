@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import android.view.View
import androidx.test.espresso.Root
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.qautomatron.kopi.library.wait.ViewMatcherWaiter
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Element waits
 */
interface ElementWaits<T> {

    val matcher: Matcher<View>
    val root: Matcher<Root>

    /**
     * Wait for element visibility
     * @param percent percent of visibility (90 by default)
     */
    fun waitForVisibility(percent: Int = 90): T {
        waitFor(ViewMatchers.isDisplayingAtLeast(percent))
        return this as T
    }

    /**
     * Wait for element invisibility
     */
    fun waitForInvisibility(): T {
        waitFor(Matchers.not(ViewMatchers.isDisplayed()))
        return this as T
    }

    /**
     * Wait for element present
     */
    fun waitForPresent(): T {
        waitFor(
            Matchers.anyOf(
                ViewMatchers.isDisplayed(),
                Matchers.not(ViewMatchers.isDisplayed())
            )
        )
        return this as T
    }

    fun waitForNotPresent(): T {
        waitFor(doesNotExist())
        return this as T
    }

    fun waitToBeSelected() = this.waitFor(ViewAssertions.matches(ViewMatchers.isSelected()))
    fun waitToBeEnabled() = this.waitFor(ViewAssertions.matches(ViewMatchers.isEnabled()))
    fun waitToBeDisabled() = this.waitFor(
        ViewAssertions.matches(
            CoreMatchers.not(
                ViewMatchers.isEnabled()
            )
        )
    )
    fun waitToBeClickable() = this.waitFor(ViewAssertions.matches(ViewMatchers.isClickable()))
    fun waitForText(text: String) = this.waitFor(withText(text))
    fun waitForText(resId: Int) = this.waitFor(withText(resId))
    fun waitForTextContains(text: String) = this.waitFor(withText(containsString(text)))

    /**
     * Wait for specific matcher
     */
    fun waitFor(
        matcher: Matcher<View>,
        timeoutInMillis: Int? = null,
        pollingInMillis: Int? = null
    ): T {
        ViewMatcherWaiter(this.matcher, root, timeoutInMillis, pollingInMillis).toMatch(matcher)
        return this as T
    }

    /**
     * Wait for specific assertion
     */
    fun waitFor(
        assertion: ViewAssertion,
        timeoutInMillis: Int? = null,
        pollingInMillis: Int? = null
    ): T {
        ViewMatcherWaiter(this.matcher, root, timeoutInMillis, pollingInMillis).toCheck(assertion)
        return this as T
    }
}