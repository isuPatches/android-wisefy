/*
 * Copyright 2021 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.sample.internal.logging

import android.util.Log
import com.isupatches.android.wisefy.shared.logging.WisefyLogger
import com.isupatches.android.wisefy.sample.BuildConfig
import java.util.Locale

private const val LOG_TAG = "WisefySample"

internal object WisefySampleLogger : WisefyLogger {

    override fun i(tag: String, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.i(LOG_TAG, createMessage(tag, message, *args))
        }
        return 0
    }

    override fun v(tag: String, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.v(LOG_TAG, createMessage(tag, message, *args))
        }
        return 0
    }

    override fun d(tag: String, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.d(LOG_TAG, createMessage(tag, message, *args))
        }
        return 0
    }

    override fun w(tag: String, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.w(LOG_TAG, createMessage(tag, message, *args))
        }
        return 0
    }

    override fun e(tag: String, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.e(LOG_TAG, createMessage(tag, message, *args))
        }
        return 0
    }

    override fun e(tag: String, throwable: Throwable, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.e(LOG_TAG, createMessage(tag, message, *args), throwable)
        }
        return 0
    }

    override fun wtf(tag: String, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            return Log.wtf(LOG_TAG, createMessage(tag, message, *args))
        }
        return 0
    }

    override fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any): Int {
        if (BuildConfig.DEBUG) {
            Log.wtf(LOG_TAG, createMessage(tag, message, *args), throwable)
        }
        return 0
    }

    /*
     * Private Helpers
     */

    private fun createMessage(tag: String, message: String, vararg args: Any): String {
        return "$tag - ${message.format(Locale.US, *args)}"
    }
}
