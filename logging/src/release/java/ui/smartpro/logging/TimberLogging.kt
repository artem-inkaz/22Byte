package com.rcosteira.logging

import android.util.Log
import timber.log.Timber

class TimberLogging : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.WARN -> logWarning(priority, tag, message)
            Log.ERROR -> logError(t, priority, tag, message)
        }
    }

    private fun logWarning(priority: Int, tag: String?, message: String) {
        FirebaseCrashlytics.getInstance().log(priority, tag, message)
    }

    private fun logError(t: Throwable?, priority: Int, tag: String?, message: String) {
        FirebaseCrashlytics.getInstance().log(priority, tag, message)

        t?.let {
            FirebaseCrashlytics.getInstance().logException(it)
        }
    }
}