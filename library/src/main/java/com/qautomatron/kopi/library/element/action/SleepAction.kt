package com.qautomatron.kopi.library.element.action

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

/**
 * Action to wait for specific amount of time
 */
object SleepAction {

    /**
     * Sleep for amount of time
     * @param millis time in millis
     */
    fun sleep(millis: Long): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for at least $millis millis"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}