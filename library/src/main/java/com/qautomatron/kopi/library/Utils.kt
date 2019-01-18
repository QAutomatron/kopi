package com.qautomatron.kopi.library

import android.content.Context
import android.support.test.InstrumentationRegistry

val appContext: Context
    get() = InstrumentationRegistry.getTargetContext()

/**
 * Return string from resources
 */
fun resourceToString(resource: Int, vararg a: Any): String {
    return appContext.getString(resource, *a)
}