package com.qautomatron.kopi.library.element

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.RootMatchers
import org.hamcrest.Matcher

/**
 * Wrapper for onView with actions
 */
class Element(override val matcher: Matcher<View>, override val root: Matcher<Root> = RootMatchers.DEFAULT) :
    ElementActions<Element>, ElementAssertions<Element>, ElementWaits<Element> {

    override val element: ViewInteraction get() = onView(matcher).inRoot(root)

    /**
     * Invoke element function
     */
    operator fun invoke(function: Element.() -> Unit) = this.function()
}