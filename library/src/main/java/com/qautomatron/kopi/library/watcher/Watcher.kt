package com.qautomatron.kopi.library.watcher

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object Watcher {

    private const val DEFAULT_TIMEOUT_LIMIT = 1000 * 60
    private const val DEFAULT_INTERVAL = 250

    var timeoutLimitInMillis = DEFAULT_TIMEOUT_LIMIT
    var watchIntervalInMillis = DEFAULT_INTERVAL

    @Throws(Exception::class)
    fun waitForCondition(instruction: Instruction) {
        var status = Status.CONDITION_NOT_MET
        var elapsedTime = 0
        do {
            if (instruction.checkCondition()) {
                status = Status.CONDITION_MET
                logCondition(true, instruction, elapsedTime)
            } else {
                elapsedTime += watchIntervalInMillis
                logCondition(false, instruction, elapsedTime)
                runBlocking { delay(watchIntervalInMillis.toLong()) }
            }
            if (elapsedTime >= timeoutLimitInMillis) {
                status = Status.TIMEOUT
                break
            }
        } while (status != Status.CONDITION_MET)
        if (status == Status.TIMEOUT) {
            throw Exception(instruction.description + " - took more than " + timeoutLimitInMillis / 1000 + " seconds. Test stopped.")
        }
    }

    enum class Status { CONDITION_NOT_MET, CONDITION_MET, TIMEOUT }

    private fun log(msg: String) = Log.d("Kopi.Watcher", msg)
    private fun logCondition(met: Boolean, instruction: Instruction, elapsedTime: Int) =
        log("Condition is <$met>.\nWhen: [${instruction.description}].\nTime: [$elapsedTime/$timeoutLimitInMillis]")
}