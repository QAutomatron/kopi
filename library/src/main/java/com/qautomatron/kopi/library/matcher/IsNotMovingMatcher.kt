package com.qautomatron.kopi.library.matcher

import android.os.SystemClock
import android.view.View
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class IsNotMovingMatcher(private val timeoutInMills: Int?) : TypeSafeMatcher<View>() {

    private val defaultTimeoutInMills: Int = 1000
    private val pollingIntervalInMills: Int = 250

    override fun matchesSafely(view: View): Boolean {
        val endTime = SystemClock.elapsedRealtime() + (timeoutInMills ?: defaultTimeoutInMills)
        val oldLocation = IntArray(2)
        val newLocation = IntArray(2)

        do {
            view.getLocationOnScreen(oldLocation)
            runBlocking { delay(pollingIntervalInMills.toLong()) }
            view.getLocationOnScreen(newLocation)

            if (newLocation.contentEquals(oldLocation)) {
                return true
            }
        } while (SystemClock.elapsedRealtime() < endTime)

        return false
    }

    override fun describeTo(description: Description) {
        description.appendText("which is stop moving before $timeoutInMills")
    }
}