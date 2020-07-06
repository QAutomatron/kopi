package com.qautomatron.kopi.sample

import androidx.test.rule.ActivityTestRule
import com.qautomatron.kopi.library.steps.DeviceSteps
import org.junit.Rule
import org.junit.Test

/**
 * Check for DeviceSteps
 */
class DeviceStepsTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun should_wait_for_activity() {
        DeviceSteps.waitForActivity("MainActivity")
    }
}
