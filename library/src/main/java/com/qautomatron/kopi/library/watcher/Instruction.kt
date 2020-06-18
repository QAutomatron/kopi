package com.qautomatron.kopi.library.watcher

import android.os.Bundle

abstract class Instruction {
    var dataContainer = Bundle()

    abstract val description: String
    abstract fun checkCondition(): Boolean
}