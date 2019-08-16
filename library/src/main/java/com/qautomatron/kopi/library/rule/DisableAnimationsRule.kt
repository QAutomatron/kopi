package com.qautomatron.kopi.library.rule

import androidx.test.uiautomator.UiDevice
import com.qautomatron.kopi.library.instrumentation
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class DisableAnimationsRule : TestWatcher() {

    override fun starting(description: Description) = disableAnimations()

    override fun finished(description: Description) = enableAnimations()

    private fun disableAnimations() {
        UiDevice.getInstance(instrumentation)
            .executeShellCommand("settings put global transition_animation_scale 0")
        UiDevice.getInstance(instrumentation)
            .executeShellCommand("settings put global window_animation_scale 0")
        UiDevice.getInstance(instrumentation)
            .executeShellCommand("settings put global animator_duration_scale 0")
    }

    private fun enableAnimations() {
        UiDevice.getInstance(instrumentation)
            .executeShellCommand("settings put global transition_animation_scale 1")
        UiDevice.getInstance(instrumentation)
            .executeShellCommand("settings put global window_animation_scale 1")
        UiDevice.getInstance(instrumentation)
            .executeShellCommand("settings put global animator_duration_scale 1")
    }
}