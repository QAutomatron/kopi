@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.uiautomator.Direction
import com.qautomatron.kopi.library.element.action.GetTextAction
import com.qautomatron.kopi.library.element.action.nestedScrollTo

/**
 * Element actions
 */
interface ElementActions<T> {

    val element: ViewInteraction

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
     * Perform action and return element
     * @param action action to perform
     */
    fun perform(vararg action: ViewAction): T {
        action.forEach { element.perform(it) }
        return this as T
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
}