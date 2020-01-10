/*
 * Copyright 2020 Patches Klinefelter
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
 * Interface for clients for logging
 *
 * @author Patches
 * @since 5.0
 */
interface WiseFyLogger {

    /**
     * Logs an info message
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun i(tag: String, message: String, vararg args: Any)

    /**
     * Logs an verbose message
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun v(tag: String, message: String, vararg args: Any)

    /**
     * Logs a debug message
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun d(tag: String, message: String, vararg args: Any)

    /**
     * Logs a warning message
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun w(tag: String, message: String, vararg args: Any)

    /**
     * Logs an error message
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun e(tag: String, message: String, vararg args: Any)

    /**
     * Logs an error message with throwable
     *
     * @param tag The tag for the log message
     * @param throwable A throwable to log with the message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun e(tag: String, throwable: Throwable, message: String, vararg args: Any)

    /**
     * Logs a terrible failure message
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun wtf(tag: String, message: String, vararg args: Any)

    /**
     * Logs a terrible failure message with throwable
     *
     * @param tag The tag for the log message
     * @param throwable A throwable to log with the message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @author Patches
     * @since 5.0
     */
    fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any)
}
