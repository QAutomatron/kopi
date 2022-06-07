package com.qautomatron.kopi.sample

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.qautomatron.kopi.library.watcher.Watcher
import org.junit.Before
import org.junit.Rule

abstract class BaseTest {
    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Watcher.DEFAULT_TIMEOUT_MILLIS = 10000
        Watcher.DEFAULT_POLLING_MILLIS = 250
    }
}