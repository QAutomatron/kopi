package com.qautomatron.kopi.library.watcher

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object Watcher {

    var DEFAULT_TIMEOUT_MILLIS = 1000 * 60
    var DEFAULT_POLLING_MILLIS = 250

    @Throws(Exception::class)
    fun waitForCondition(
        instruction: Instruction,
        timeoutInMillis: Int?,
        pollingInMillis: Int?
    ) {
        val timeoutTimeInMillis = timeoutInMillis ?: DEFAULT_TIMEOUT_MILLIS
        val pollingTimeInMillis = pollingInMillis ?: DEFAULT_POLLING_MILLIS

        var status = Status.CONDITION_NOT_MET
        var elapsedTime = 0
        do {
            if (instruction.checkCondition()) {
                status = Status.CONDITION_MET
                logCondition(true, instruction, elapsedTime, timeoutTimeInMillis)
            } else {
                elapsedTime += pollingTimeInMillis
                logCondition(false, instruction, elapsedTime, timeoutTimeInMillis)
                runBlocking { delay(pollingTimeInMillis.toLong()) }
            }
            if (elapsedTime >= timeoutTimeInMillis) {
                status = Status.TIMEOUT
                break
            }
        } while (status != Status.CONDITION_MET)
        if (status == Status.TIMEOUT) {
            throw Exception(instruction.description + " - took more than " + timeoutTimeInMillis / 1000 + " seconds. Test stopped.")
        }
    }

    enum class Status { CONDITION_NOT_MET, CONDITION_MET, TIMEOUT }

    private fun log(msg: String) = Log.d("Kopi.Watcher", msg)
    private fun logCondition(
        met: Boolean,
        instruction: Instruction,
        elapsedTime: Int,
        timeoutTimeInMillis: Int
    ) =
        log("Condition is <$met>.\nWhen: [${instruction.description}].\nTime: [$elapsedTime/$timeoutTimeInMillis]")
}