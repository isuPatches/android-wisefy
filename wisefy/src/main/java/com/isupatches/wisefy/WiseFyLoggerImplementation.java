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
import android.util.Log;

import com.isupatches.wisefy.annotations.Internal;

/**
 * A class that implements the logging strategy desired for WiseFy.
 *
 * @see WiseFyLoggingStrategy
 *
 * @author Patches
 */
@Internal
public class WiseFyLoggerImplementation implements WiseFyLoggingStrategy {

  private static final int MAX_TAG_LENGTH = 23;

  private final boolean loggingEnabled;

  /**
   * Constructor.
   *
   * @param isLoggingEnabled - Whether logging should be enabled for the the logging implementation.
   */
  WiseFyLoggerImplementation(final boolean isLoggingEnabled) {
    this.loggingEnabled = isLoggingEnabled;
  }

  /**
   * To return if logging is enabled for the WiseFyLoggerImplementation.
   *
   * @return bool - If logging is enabled for the instance of WiseFy
   */
  final boolean isLoggingEnabled() {
    return loggingEnabled;
  }

  /**
   * Logs a debug message.
   *
   * @param tag The tag for the log message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #isLoggable(String, int)
   * @see Log#d(String, String)
   */
  @Override
  public void debug(@NonNull final String tag, @NonNull final String message, @NonNull final Object... args) {
    if (isLoggable(tag, Log.DEBUG)) {
      Log.d(tag, String.format(message, args));
    }
  }

  /**
   * Logs an error message.
   *
   * @param tag The tag for the log message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #isLoggable(String, int)
   * @see Log#e(String, String)
   */
  @Override
  public void error(@NonNull final String tag, @NonNull final String message, @NonNull final Object... args) {
    if (isLoggable(tag, Log.ERROR)) {
      Log.e(tag, String.format(message, args));
    }
  }

  /**
   * Logs an error message with a throwable.
   *
   * @param tag The tag for the log message
   * @param throwable A throwable to log with the message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #isLoggable(String, int)
   * @see Log#e(String, String, Throwable)
   */
  @Override
  public void error(@NonNull final String tag, @NonNull final Throwable throwable, @NonNull final String message, @NonNull final Object... args) {
    if (isLoggable(tag, Log.ERROR)) {
      Log.e(tag, String.format(message, args), throwable);
    }
  }

  /**
   * Logs a warning message.
   *
   * @param tag The tag for the log message
   * @param message The message to log (can include placeholders)
   * @param args The formatting arguments for the log message
   *
   * @see #isLoggable(String, int)
   * @see Log#w(String, String)
   */
  @Override
  public void warn(@NonNull final String tag, @NonNull final String message, @NonNull final Object... args) {
    if (isLoggable(tag, Log.WARN)) {
      Log.w(tag, String.format(message, args));
    }
  }

  /*
   * HELPERS
   */

  /**
   * Checks to see given a TAG, log level, and if logging is enabled if logging should occur.
   *
   * @param tag The tag to be used for the log
   * @param level The level of logging (i.error Log.DEBUG, Log.WARN, Log.ERROR, etc)
   *
   * @return boolean - True if logging should occur based off level and other factors
   *
   * @see Log#isLoggable(String, int)
   */
  private boolean isLoggable(@NonNull final String tag, final int level) {
    final boolean loggable;
    String tagToUse = tag;
    if (loggingEnabled) {
      loggable = true;
    } else {
      if (tag.length() > MAX_TAG_LENGTH) {
        tagToUse = tag.substring(0, MAX_TAG_LENGTH - 1);
      }
      loggable = Log.isLoggable(tagToUse, level);
    }
    return loggable;
  }
}
