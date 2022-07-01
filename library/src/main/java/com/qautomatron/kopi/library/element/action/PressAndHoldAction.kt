package com.qautomatron.kopi.library.element.action

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.MotionEvents
import androidx.test.espresso.action.Press
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class PressAndHoldAction : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isCompletelyDisplayed()
    }

    override fun getDescription(): String {
        return "Press and hold the button"
    }

    override fun perform(uiController: UiController, view: View) {
        val coordinates = GeneralLocation.CENTER.calculateCoordinates(view)
        val precision = Press.FINGER.describePrecision()
        MotionEvents.sendDown(uiController, coordinates, precision).down
    }
}
