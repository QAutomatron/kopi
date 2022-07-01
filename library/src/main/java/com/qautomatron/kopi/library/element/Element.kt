package com.qautomatron.kopi.library.element

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers
import org.hamcrest.Matcher

/**
 * Wrapper for onView with actions
 */
class Element(
    override val matcher: Matcher<View>,
    override val root: Matcher<Root> = RootMatchers.DEFAULT
) : ElementActions<Element>, ElementAssertions<Element>, ElementWaits<Element> {

    override val element: ViewInteraction get() = onView(matcher).inRoot(root)

    /**
     * Create new Element using current one as descendant
     */
    fun descendant(matcher: Matcher<View>, root: Matcher<Root> = this.root) = Element(
        Matchers.allOf(
            matcher,
            ViewMatchers.isDescendantOfA(this.matcher)
        ),
        root
    )

    /**
     * Create new Element using current one as parent
     */
    fun child(matcher: Matcher<View>, root: Matcher<Root> = this.root) = Element(
        Matchers.allOf(
            matcher,
            ViewMatchers.withParent(this.matcher)
        ),
        root
    )

    /**
     * Invoke element function
     */
    operator fun invoke(function: Element.() -> Unit) = this.function()
}
