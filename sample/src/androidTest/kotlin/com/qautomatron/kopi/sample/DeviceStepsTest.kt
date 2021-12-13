package com.qautomatron.kopi.sample

import com.qautomatron.kopi.library.steps.DeviceSteps
import org.junit.Test

/**
 * Check for DeviceSteps
 */
class DeviceStepsTest : BaseTest() {

    @Test
    fun should_wait_for_activity() {
        DeviceSteps.waitForActivity("com.qautomatron.kopi.sample.MainActivity")
    }
}
