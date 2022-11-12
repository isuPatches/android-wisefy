/*
 * Copyright 2022 Patches Klinefelter
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
 * A no-op, default implementation of [WisefyLogger].
 *
 * @see WisefyLogger
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
class DefaultWisefyLogger : WisefyLogger {

    /**
     * A function that logs an information message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun i(tag: String, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs a verbose message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun v(tag: String, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs a debug message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun d(tag: String, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs a warning message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun w(tag: String, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs an error message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun e(tag: String, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs an error message.
     *
     * @param tag The tag for the log message
     * @param throwable An exception to include with the error log
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun e(tag: String, throwable: Throwable, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs a "what a terrible failure" message.
     *
     * @param tag The tag for the log message
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun wtf(tag: String, message: String, vararg args: Any): Int = 0

    /**
     * A function that logs a "what a terrible failure" message.
     *
     * @param tag The tag for the log message
     * @param throwable An exception to include with the "what a terrible failure" log
     * @param message The message to log (can include placeholders)
     * @param args The formatting arguments for the log message
     *
     * @return Int - 0 bytes logged
     *
     * @author Patches Klinefelter
     * @since 11/2022, version 5.0.0
     */
    override fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any): Int = 0
}
