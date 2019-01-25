@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import android.support.test.espresso.Root
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import com.qautomatron.kopi.library.wait.ViewMatcherWaiter
import org.hamcrest.Matcher
import org.hamcrest.Matchers

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
    fun waitForInvisibility(): T{
        waitFor(Matchers.not(ViewMatchers.isDisplayed()))
        return this as T
    }

    /**
     * Wait for element present
     */
    fun waitForPresent(): T {
        waitFor(Matchers.anyOf(ViewMatchers.isDisplayed(), Matchers.not(ViewMatchers.isDisplayed())))
        return this as T
    }

    /**
     * Wait for specific matcher
     */
    fun waitFor(matcher: Matcher<View>): T {
        ViewMatcherWaiter(this.matcher).toMatch(matcher, root)
        return this as T
    }
}