@file:Suppress("UNCHECKED_CAST")

package com.qautomatron.kopi.library.element

import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.uiautomator.Direction
import com.qautomatron.kopi.library.element.action.GetTextAction
import com.qautomatron.kopi.library.element.action.nestedScrollTo

interface ElementActions<T> {

    val element: ViewInteraction

    /**
     * Replace text in element
     */
    fun replace(text: String) = perform(ViewActions.replaceText(text))

    /**
     * Tap on element
     */
    fun tap() = perform(ViewActions.click())

    /**
     * Double tap on element
     */
    fun doubleTap() = perform(ViewActions.doubleClick())

    /**
     * Long tap on element
     */
    fun longTap() = perform(ViewActions.longClick())

    /**
     * Type text into element
     * @param text text to type
     * @param clear clear field before type (true/false)
     * @param hideKeyboard hide keyboard after type (true/false)
     */
    fun type(text: String, clear: Boolean = false, hideKeyboard: Boolean = false) {
        if (clear) clear()
        perform(ViewActions.typeText(text))
        if (hideKeyboard) ViewActions.closeSoftKeyboard()
    }

    /**
     * Scroll to element (scrollview)
     * @param nested is element in nestedview (true/false)
     */
    fun scroll(nested: Boolean = false): Element {
        if (nested) {
            perform(nestedScrollTo())
        } else {
            perform(ViewActions.scrollTo())
        }
        return this as Element
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
    fun clear() = perform(ViewActions.clearText())

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
            Direction.LEFT -> perform(ViewActions.swipeLeft())
            Direction.RIGHT -> perform(ViewActions.swipeRight())
            Direction.UP -> perform(ViewActions.swipeUp())
            Direction.DOWN -> perform(ViewActions.swipeDown())
        }
    }
}