/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.logging

/**
 * Logging helper class.
 *
 * @author Patches
 * @since 3.0
 */
@Suppress("SpreadOperator")
internal object WiseFyLogger {

    private var wisefyLoggerImplementation: WiseFyLoggerImplementation? = null

    /**
     * To return if logging is enabled for the WiseFyLoggerImplementation.
     *
     * @return bool - If logging is enabled for the instance of WiseFy
     *
     * @see [ensureWiseFyLoggerImplementationExists]
     * @see [WiseFyLoggerImplementation.isLoggingEnabled]
     */
    fun isLoggingEnabled(): Boolean {
        ensureWiseFyLoggerImplementationExists()
        return wisefyLoggerImplementation?.isLoggingEnabled ?: false
    }

    /**
     * Creates and sets the WiseFyLoggerImplementation to use when logging.
     *
     * @param loggingEnabled - Whether logging should be enabled for the WiseFyLoggerImplementation
     *
     * @see WiseFyLoggerImplementation
     */
    fun configureWiseFyLoggerImplementation(loggingEnabled: Boolean) {
        wisefyLoggerImplementation = WiseFyLoggerImplementation(loggingEnabled)
    }

    /**
     * Logs a debug message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @see [ensureWiseFyLoggerImplementationExists]
     * @see [WiseFyLoggerImplementation.debug]
     */
    fun debug(tag: String, message: String, vararg args: Any) {
        ensureWiseFyLoggerImplementationExists()
        wisefyLoggerImplementation?.debug(tag, message, *args)
    }

    /**
     * Logs an error message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @see [ensureWiseFyLoggerImplementationExists]
     * @see [WiseFyLoggerImplementation.error]
     */
    fun error(tag: String, message: String, vararg args: Any) {
        ensureWiseFyLoggerImplementationExists()
        wisefyLoggerImplementation?.error(tag, message, *args)
    }

    /**
     * Logs an error message with a throwable.
     *
     * @param tag The tag for the log message
     * @param throwable A throwable to log with the message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @see [ensureWiseFyLoggerImplementationExists]
     * @see [WiseFyLoggerImplementation.error]
     */
    fun error(tag: String, throwable: Throwable, message: String, vararg args: Any) {
        ensureWiseFyLoggerImplementationExists()
        wisefyLoggerImplementation?.error(tag, throwable, message, *args)
    }

    /**
     * Logs a warning message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @see [ensureWiseFyLoggerImplementationExists]
     * @see [WiseFyLoggerImplementation.warn]
     */
    fun warn(tag: String, message: String, vararg args: Any) {
        ensureWiseFyLoggerImplementationExists()
        wisefyLoggerImplementation?.warn(tag, message, *args)
    }

    /*
     * HELPERS
     */

    /**
     * If a WiseFyLoggerImplementation is not already configured or set, it will create
     * a new instance with the default value of false for logging enabled.
     *
     * @see [configureWiseFyLoggerImplementation]
     */
    private fun ensureWiseFyLoggerImplementationExists() {
        if (wisefyLoggerImplementation == null) {
            configureWiseFyLoggerImplementation(false)
        }
    }
}
