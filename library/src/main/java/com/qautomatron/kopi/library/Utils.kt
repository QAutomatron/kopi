package com.qautomatron.kopi.library

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

val appContext: Context
    get() = InstrumentationRegistry.getInstrumentation().targetContext

/**
 * Return string from resources
 */
fun resourceToString(resource: Int, vararg a: Any): String {
    return appContext.getString(resource, *a)
}