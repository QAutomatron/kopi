package com.qautomatron.kopi.library.steps

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.qautomatron.kopi.library.element.action.SleepAction
import com.qautomatron.kopi.library.instrumentation
import com.qautomatron.kopi.library.mDevice
import com.qautomatron.kopi.library.wait.WaitForActivity
import com.qautomatron.kopi.library.watcher.Watcher
import org.junit.Assert.assertTrue

/**
 * Device related steps
 */
object DeviceSteps {

    /**
     * Wait for activity by name
     */
    fun waitForActivity(
        fullName: String,
        timeoutInMillis: Int? = null,
        pollingInMillis: Int? = null
    ) =
        Watcher.waitForCondition(WaitForActivity(fullName), timeoutInMillis, pollingInMillis)

    /**
     *  Will pause test execution for defined second
     *  Use only if there is need to do nothing for some time
     *  Otherwise use "waitfor" matchers, when there is end of waiting condition
     */
    fun makePause(secs: Long) = onView(isRoot()).perform(SleepAction.sleep(secs * 1000))

    /**
     * Close keyboard
     */
    fun closeKeyboard() = Espresso.closeSoftKeyboard()

    /**
     * Press Back
     */
    fun pressBack() = Espresso.pressBack()

    /**
     * Will use UI Automator to wait for package with name to be active
     */
    fun packageShouldBeOpened(packageTitle: String, timeoutInSec: Int = 5) {
        assertTrue(
            "Package <$packageTitle> should be present on screen",
            mDevice.wait(
                Until.hasObject(By.pkg(packageTitle).depth(0)),
                (timeoutInSec * 1000).toLong()
            )
        )
    }

    /**
     * Press the home button with uiautomator to minimize previous apps and don't
     * allow them to interrupt with the test app
     */
    fun pressHome() {
        try {
            mDevice.pressHome()
        } catch (ex: NoActivityResumedException) {
        }
    }

    /**
     * Wait for idle state
     */
    fun waitForIdle(timeoutInMills: Long = 10000) {
        mDevice.waitForIdle(timeoutInMills)
    }
}