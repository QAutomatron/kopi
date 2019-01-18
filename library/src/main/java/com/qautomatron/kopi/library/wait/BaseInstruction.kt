package com.qautomatron.kopi.library.wait

import android.app.Activity
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage.RESUMED
import com.azimolabs.conditionwatcher.Instruction

/**
 * Base instruction to provide activity to watchers
 */
abstract class BaseInstruction : Instruction() {

    private var currentActivity: Activity? = null

    internal val activityInstance: Activity?
        get() {
            getInstrumentation().runOnMainSync {
                val resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED)
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = resumedActivities.iterator().next()
                }
            }
            return currentActivity
        }
}
