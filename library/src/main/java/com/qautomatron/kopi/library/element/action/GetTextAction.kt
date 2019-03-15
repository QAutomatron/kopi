package com.qautomatron.kopi.library.element.action

import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher

/**
 * Action to get element text
 */
class GetTextAction : ViewAction {

    lateinit var text: CharSequence

    override fun getConstraints(): Matcher<View> = isAssignableFrom(TextView::class.java)

    override fun getDescription() = "get text"

    override fun perform(uiController: UiController, view: View) {
        val textView = view as TextView
        text = textView.text
    }
}