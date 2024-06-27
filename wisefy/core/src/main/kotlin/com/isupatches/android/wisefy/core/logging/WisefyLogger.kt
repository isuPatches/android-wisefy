/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.core.logging

/**
 * A logging interface that can be provided to Wisefy to log messages.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface WisefyLogger {

    /**
     * A function that logs an information message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun i(tag: String, message: String, vararg args: Any): Int

    /**
     * A function that logs a verbose message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun v(tag: String, message: String, vararg args: Any): Int

    /**
     * A function that logs a debug message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun d(tag: String, message: String, vararg args: Any): Int

    /**
     * A function that logs a warning message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun w(tag: String, message: String, vararg args: Any): Int

    /**
     * A function that logs an error message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun e(tag: String, message: String, vararg args: Any): Int

    /**
     * A function that logs an error message.
     *
     * @param tag The tag for the log message
     * @param throwable An exception to include with the error log
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun e(tag: String, throwable: Throwable, message: String, vararg args: Any): Int

    /**
     * A function that logs a "what a terrible failure" message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun wtf(tag: String, message: String, vararg args: Any): Int

    /**
     * A function that logs a "what a terrible failure" message.
     *
     * @param tag The tag for the log message
     * @param throwable An exception to include with the "what a terrible failure" log
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - The amount of bytes logged
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any): Int
}
