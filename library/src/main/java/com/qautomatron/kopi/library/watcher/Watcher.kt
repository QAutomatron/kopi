package com.qautomatron.kopi.library.watcher

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

object Watcher {

    private const val DEFAULT_TIMEOUT_LIMIT = 1000 * 60
    private const val DEFAULT_INTERVAL = 250

    var timeoutLimit = DEFAULT_TIMEOUT_LIMIT
    var watchInterval = DEFAULT_INTERVAL

    @Throws(Exception::class)
    fun waitForCondition(instruction: Instruction) {
        var status = Status.CONDITION_NOT_MET
        var elapsedTime = 0
        do {
            if (instruction.checkCondition()) {
                status = Status.CONDITION_MET
            } else {
                elapsedTime += watchInterval
                runBlocking { delay(watchInterval.toLong()) }
            }
            if (elapsedTime >= timeoutLimit) {
                status = Status.TIMEOUT
                break
            }
        } while (status != Status.CONDITION_MET)
        if (status == Status.TIMEOUT) throw Exception(instruction.description + " - took more than " + timeoutLimit / 1000 + " seconds. Test stopped.")
    }

    enum class Status {
        CONDITION_NOT_MET, CONDITION_MET, TIMEOUT
    }
}