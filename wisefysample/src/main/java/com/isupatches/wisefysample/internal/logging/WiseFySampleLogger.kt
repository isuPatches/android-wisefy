package com.isupatches.wisefysample.internal.logging

import android.os.Build
import android.util.Log
import com.isupatches.wisefy.logging.WiseFyLogger
import java.util.Locale

private const val LOG_TAG = "WiseFySample"

internal object WiseFySampleLogger : WiseFyLogger {

    private const val MAX_TAG_LENGTH = 23

    override fun i(tag: String, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.INFO)) {
            Log.i(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun v(tag: String, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.VERBOSE)) {
            Log.v(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun d(tag: String, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.DEBUG)) {
            Log.d(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun w(tag: String, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.WARN)) {
            Log.w(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun e(tag: String, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.ERROR)) {
            Log.e(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun e(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.ERROR)) {
            Log.e(LOG_TAG, createMessage(tag, message, args), throwable)
        }
    }

    override fun wtf(tag: String, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.ERROR)) {
            Log.wtf(LOG_TAG, createMessage(tag, message, args))
        }
    }

    override fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        if (isLoggable(LOG_TAG, Log.ERROR)) {
            Log.wtf(LOG_TAG, createMessage(tag, message, args), throwable)
        }
    }

    /*
     * Private Helpers
     */

    private fun createMessage(tag: String, message: String, vararg args: Any): String {
        return "$tag - ${message.format(Locale.US, *args)}"
    }

    private fun isLoggable(tag: String, level: Int): Boolean {
        val loggable: Boolean
        var tagToUse = tag
        // As of API 24, Log tags are no longer limited to 23 chars.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N && tag.length > MAX_TAG_LENGTH) {
            tagToUse = tag.substring(0, MAX_TAG_LENGTH - 1)
        }
        loggable = Log.isLoggable(tagToUse, level)
        return loggable
    }
}
