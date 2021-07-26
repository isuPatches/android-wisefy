package com.isupatches.android.wisefy.sample.internal.logging

import android.util.Log
import com.isupatches.wisefy.logging.WiseFyLogger
import java.util.Locale

private const val LOG_TAG = "WiseFySample"

internal object WiseFySampleLogger : WiseFyLogger {

    override fun i(tag: String, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.INFO)) {
            Log.i(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun v(tag: String, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.VERBOSE)) {
            Log.v(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun d(tag: String, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.DEBUG)) {
            Log.d(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun w(tag: String, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.WARN)) {
            Log.w(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun e(tag: String, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.ERROR)) {
            Log.e(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun e(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.ERROR)) {
            Log.e(LOG_TAG, createMessage(tag, message, args), throwable)
        }
    }

    override fun wtf(tag: String, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.ERROR)) {
            Log.wtf(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        if (Log.isLoggable(LOG_TAG, Log.ERROR)) {
            Log.wtf(LOG_TAG, createMessage(tag, message, args), throwable)
        }
    }

    /*
     * Private Helpers
     */

    private fun createMessage(tag: String, message: String, vararg args: Any): String {
        return "$tag - ${message.format(Locale.US, *args)}"
    }
}
