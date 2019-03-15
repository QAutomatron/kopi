package com.qautomatron.kopi.library.element.action

import android.view.View
import android.view.ViewParent
import android.widget.FrameLayout
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Action to perform scroll to element in NestedScrollView
 */
fun nestedScrollTo(): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View> {
            return Matchers.allOf(
                ViewMatchers.isDescendantOfA(ViewMatchers.isAssignableFrom(NestedScrollView::class.java)),
                ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
            )
        }

        override fun getDescription() = "View is not NestedScrollView"

        override fun perform(uiController: UiController, view: View) {
            try {
                val nestedScrollView =
                    findFirstParentLayoutOfClass(view, NestedScrollView::class.java) as? NestedScrollView?
                if (nestedScrollView != null) {
                    nestedScrollView.scrollTo(0, view.top)
                } else {
                    throw Exception("Unable to find NestedScrollView parent.")
                }
            } catch (e: Exception) {
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(e)
                    .build()
            }
            uiController.loopMainThreadUntilIdle()
        }
    }
}

private fun findFirstParentLayoutOfClass(view: View, parentClass: Class<out View>): View? {
    var parent: ViewParent? = FrameLayout(view.context)
    var incrementView: ViewParent? = null
    var i = 0
    while (parent != null && parent.javaClass != parentClass) {
        parent = if (i == 0) {
            findParent(view)
        } else {
            findParent(incrementView!!)
        }
        incrementView = parent
        i++
    }
    return parent as? View?
}

private fun findParent(view: View) = view.parent
private fun findParent(view: ViewParent) = view.parent
