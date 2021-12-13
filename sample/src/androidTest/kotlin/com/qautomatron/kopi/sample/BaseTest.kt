package com.qautomatron.kopi.sample

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule

abstract class BaseTest {
    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
}