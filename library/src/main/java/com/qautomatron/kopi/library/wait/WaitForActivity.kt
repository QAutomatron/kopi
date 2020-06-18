package com.qautomatron.kopi.library.wait

/**
 * Will wait for activity
 */
class WaitForActivity(private val name: String) : BaseInstruction() {

    override val description: String
        get() = "Current activity should be same as <$name> but was <${activityInstance?.localClassName}>"

    override fun checkCondition(): Boolean {
        val activity = activityInstance ?: return false
        return activity.localClassName == name
    }
}
