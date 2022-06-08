package com.qautomatron.kopi.library.matcher

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class NthChildOfMatcher(val parentMatcher: Matcher<View>, val childPosition: Int) : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("with $childPosition child view of type parentMatcher")
    }

    override fun matchesSafely(view: View): Boolean {
        if (view.parent !is ViewGroup) {
            return parentMatcher.matches(view.parent)
        }
        val group = view.parent as ViewGroup
        return parentMatcher.matches(view.parent) && group.getChildAt(childPosition) == view
    }
}