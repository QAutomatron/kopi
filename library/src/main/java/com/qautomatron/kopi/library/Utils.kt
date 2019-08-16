package com.qautomatron.kopi.library

import android.app.Instrumentation
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

val instrumentation: Instrumentation
    get() = InstrumentationRegistry.getInstrumentation()

val appContext: Context
    get() = instrumentation.targetContext

/**
 * Return string from resources
 */
fun resourceToString(resource: Int, vararg a: Any): String = appContext.getString(resource, *a)
