package com.qautomatron.kopi.library.element

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Root
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.RootMatchers
import android.view.View
import org.hamcrest.Matcher

/**
 * Wrapper for onView with actions
 */
class Element(override val matcher: Matcher<View>, override val root: Matcher<Root> = RootMatchers.DEFAULT) :
    ElementActions<Element>, ElementAssertions<Element>, ElementWaits<Element> {

    override val element: ViewInteraction
        get() = onView(matcher).inRoot(root)

    /**
     * Invoke element function
     */
    operator fun invoke(function: Element.() -> Unit) = this.function()
}