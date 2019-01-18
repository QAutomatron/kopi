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
import org.hamcrest.Matchers.not

class Element(private val matcher: Matcher<View>, private val root: Matcher<Root>? = null) {

    val element: ViewInteraction
        get() {
            return if (root != null) {
                onView(matcher).inRoot(root)
            } else {
                onView(matcher)
            }
        }

    fun shouldBePresent() = check(matches(isDisplayed()))

    fun shouldNotExist() = check(doesNotExist())

    fun shouldBeDisplayed(value: Int = 100) = check(matches(isDisplayingAtLeast(value)))

    fun shouldNotBeDisplayed() = check(matches(not(isCompletelyDisplayed())))

    fun shouldBeEnabled() = check(matches(isEnabled()))

    fun shouldNotBeEnabled() = check(matches(not(isEnabled())))

    fun shouldBeChecked() = waitForPresent().check(matches(isChecked()))

    fun shouldNotBeChecked() = waitForPresent().check(matches(isNotChecked()))

    fun sameAs(text: String) = waitForPresent().check(matches(withText(text)))

    fun sameAs(resourceId: Int) = waitForPresent().check(matches(withText(resourceId)))

    fun check(assertion: ViewAssertion): Element {
        element.check(assertion)
        return this
    }

    fun perform(vararg action: ViewAction): Element {
        element.perform(*action)
        return this
    }

    fun type(text: String, clear: Boolean = false, hideKeyboard: Boolean = false) {
        if (clear) clear()
        perform(typeText(text))
        if (hideKeyboard) closeSoftKeyboard()
    }

    fun replace(text: String) = perform(replaceText(text))

    fun tap() = perform(click())

    fun scroll(nested: Boolean = false): Element {
        if (nested) {
            perform(nestedScrollTo())
        } else {
            perform(scrollTo())
        }
        return this
    }

    fun getText(): String {
        val action = GetTextAction()
        perform(action)
        return action.getTexts().toString()
    }

    fun clear() = perform(clearText())

    fun waitForVisibility(percent: Int = 90): Element {
        waitFor(isDisplayingAtLeast(percent))
        return this
    }

    fun waitForInvisibility(): Element {
        waitFor(not(isDisplayed()))
        return this
    }

    fun waitForPresent(): Element {
        waitFor(isDisplayed())
        return this
    }

    fun swipe(direction: Direction) {
        when (direction) {
            Direction.LEFT -> perform(swipeLeft())
            Direction.RIGHT -> perform(swipeRight())
            Direction.UP -> perform(swipeUp())
            Direction.DOWN -> perform(swipeDown())
        }
    }

    fun waitFor(matcher: Matcher<View>): Element {
        ViewMatcherWaiter(this.matcher).toMatch(matcher, this.root)
        return this
    }

    operator fun invoke(function: Element.() -> Unit) = this.function()
}