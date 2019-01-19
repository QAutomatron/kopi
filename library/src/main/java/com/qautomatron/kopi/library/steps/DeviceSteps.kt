package com.qautomatron.kopi.library.steps

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.Until
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.qautomatron.kopi.library.element.action.SleepAction
import com.qautomatron.kopi.library.wait.WaitForActivity
import org.junit.Assert.assertTrue

/**
 * Device related steps
 */
object DeviceSteps {

    /**
     * Wait for activity by name
     */
    fun waitForActivity(fullName: String) {
        ConditionWatcher.waitForCondition(WaitForActivity(fullName))
    }

    /**
     * Will use UI Automator to wait for package with name to be active
     */
    fun packageShouldBeOpened(packageTitle: String, timeoutInSec: Int = 5) {
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        assertTrue(
            "Package <$packageTitle> should be present on screen",
            mDevice.wait(Until.hasObject(By.pkg(packageTitle).depth(0)), (timeoutInSec * 1000).toLong())
        )
    }

    /**
     *  Will pause test execution for defined second
     *  Use only if there is need to do nothing for some time
     *  Otherwise use "waitfor" matchers, when there is end of waiting condition
     */
    fun makePause(secs: Long) {
        onView(isRoot()).perform(SleepAction.sleep(secs * 1000))
    }

    /**
     * Close keyboard
     */
    fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
    }

    /**
     * Press Back
     */
    fun pressBack() {
        Espresso.pressBack()
    }
}