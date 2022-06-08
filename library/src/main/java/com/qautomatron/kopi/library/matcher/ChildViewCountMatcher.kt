package com.qautomatron.kopi.library.matcher

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withChildViewCount(count: Int, childMatcher: Matcher<View?>): Matcher<View?> {
    return object : BoundedMatcher<View?, ViewGroup>(ViewGroup::class.java) {
        override fun matchesSafely(viewGroup: ViewGroup): Boolean {
            var matchCount = 0
            for (i in 0 until viewGroup.childCount) {
                if (childMatcher.matches(viewGroup.getChildAt(i))) {
                    matchCount++
                }
            }
            return matchCount == count
        }

        override fun describeTo(description: Description) {
            description.appendText("ViewGroup with child-count=$count and")
            childMatcher.describeTo(description)
        }
    }
}