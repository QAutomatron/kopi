package com.qautomatron.kopi.library.wait

import android.app.Activity
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.azimolabs.conditionwatcher.Instruction
import com.qautomatron.kopi.library.instrumentation

/**
 * Base instruction to provide activity to watchers
 */
abstract class BaseInstruction : Instruction() {

    private var currentActivity: Activity? = null

    internal val activityInstance: Activity?
        get() {
            instrumentation.runOnMainSync {
                val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = resumedActivities.iterator().next()
                }
            }
            return currentActivity
        }
}
