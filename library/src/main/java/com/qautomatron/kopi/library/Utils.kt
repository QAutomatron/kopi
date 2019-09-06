package com.qautomatron.kopi.library

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

val instrumentation: Instrumentation get() = InstrumentationRegistry.getInstrumentation()
val targetContext: Context get() = instrumentation.targetContext
val appContext: Context get() = targetContext.applicationContext

/**
 * Return string from resources
 */
fun resourceToString(resource: Int, vararg a: Any): String = appContext.getString(resource, *a)
