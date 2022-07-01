@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import android.os.SystemClock
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.uiautomator.Direction
import com.qautomatron.kopi.library.element.action.GetTextAction
import com.qautomatron.kopi.library.element.action.PressAndHoldAction
import com.qautomatron.kopi.library.element.action.nestedScrollTo
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Element actions
 */
interface ElementActions<T> {

    val element: ViewInteraction

    /**
     * Perform action with retry and return element
     * @param action action to perform
     */
    fun perform(vararg action: ViewAction): T {
        action.forEach {
            var result: Boolean
            var exception: Throwable? = null
            val endTime = SystemClock.elapsedRealtime() + ElementActionConfig.timeout
            do {
                result = true
                element.withFailureHandler { error, _ ->
                    if (error::class.java in ElementActionConfig.allowedExceptions) {
                        result = false
                        exception = error
                    } else throw error
                }.perform(it)
                if (!result) runBlocking { delay(ElementActionConfig.interval) }
            } while (SystemClock.elapsedRealtime() < endTime && !result)
            if (!result && exception != null) {
                throw exception as Throwable
            }
        }
        return this as T
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
     * Press and hold on element
     */
    fun pressAndHold() = perform(PressAndHoldAction())

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
     * Scroll to element (scrollview)
     * @param nested is element in nested view (true/false)
     */
    fun scroll(nested: Boolean = false): T {
        if (nested) {
            perform(nestedScrollTo())
        } else {
            perform(scrollTo())
        }
        return this as T
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
}