/*
 * Copyright 2017 Patches Klinefelter
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
package com.isupatches.wisefy;

import android.support.annotation.NonNull;

import com.isupatches.wisefy.annotations.Internal;

/**
 * Logging helper class.
 *
 * @author Patches
 */
@Internal
final class WiseFyLogger {

  private static WiseFyLoggerImplementation wisefyLoggerImplementation;

  /**
   * Private constructor.
   */
  private WiseFyLogger() {
    // No-op
  }

  /**
   * Creates and sets the WiseFyLoggerImplementation to use when logging.
   *
   * @param loggingEnabled - Whether logging should be enabled for the WiseFyLoggerImplementation
   *
   * @see WiseFyLoggerImplementation
   */
  static void configureWiseFyLoggerImplementation(final boolean loggingEnabled) {
    wisefyLoggerImplementation = new WiseFyLoggerImplementation(loggingEnabled);
  }

  /**
   * Logs a debug message.
   *
   * @param tag The tag for the log message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #ensureWiseFyLoggerImplementationExists()
   * @see WiseFyLoggerImplementation#debug(String, String, Object...)
   */
  static void debug(@NonNull final String tag, @NonNull final String message, @NonNull final Object...args) {
    ensureWiseFyLoggerImplementationExists();
    wisefyLoggerImplementation.debug(tag, message, args);
  }

  /**
   * To return if logging is enabled for the WiseFyLoggerImplementation.
   *
   * @return bool - If logging is enabled for the instance of WiseFy
   *
   * @see #ensureWiseFyLoggerImplementationExists()
   * @see WiseFyLoggerImplementation#isLoggingEnabled()
   */
  static boolean isLoggingEnabled() {
    ensureWiseFyLoggerImplementationExists();
    return wisefyLoggerImplementation.isLoggingEnabled();
  }

  /**
   * Logs an error message.
   *
   * @param tag The tag for the log message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #ensureWiseFyLoggerImplementationExists()
   * @see WiseFyLoggerImplementation#error(String, String, Object...)
   */
  static void error(@NonNull final String tag, @NonNull final String message, @NonNull final Object...args) {
    ensureWiseFyLoggerImplementationExists();
    wisefyLoggerImplementation.error(tag, message, args);
  }

  /**
   * Logs an error message with a throwable.
   *
   * @param tag The tag for the log message
   * @param throwable A throwable to log with the message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #ensureWiseFyLoggerImplementationExists()
   * @see WiseFyLoggerImplementation#error(String, Throwable, String, Object...)
   */
  static void error(@NonNull final String tag, @NonNull final Throwable throwable, @NonNull final String message, @NonNull final Object...args) {
    ensureWiseFyLoggerImplementationExists();
    wisefyLoggerImplementation.error(tag, throwable, message, args);
  }

  /**
   * Logs a warning message.
   *
   * @param tag The tag for the log message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #ensureWiseFyLoggerImplementationExists()
   * @see WiseFyLoggerImplementation#warn(String, String, Object...)
   */
  static void warn(@NonNull final String tag, @NonNull final String message, @NonNull final Object...args) {
    ensureWiseFyLoggerImplementationExists();
    wisefyLoggerImplementation.warn(tag, message, args);
  }

  /*
   * HELPERS
   */

  /**
   * If a WiseFyLoggerImplementation is not already configured or set, it will create set one
   * with the default value of false for logging enabled.
   *
   * @see #configureWiseFyLoggerImplementation(boolean)
   */
  private static void ensureWiseFyLoggerImplementationExists() {
    if (wisefyLoggerImplementation == null) {
      configureWiseFyLoggerImplementation(false);
    }
  }
}
