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
package com.isupatches.wisefy.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.constants.Symbols;

/**
 * A helper class with methods pertaining to String manipulations and null checks.
 *
 * @author Patches
 */
@Internal
public final class StringUtil {

  /**
   * Private constructor.
   */
  private StringUtil() {
    // No-op
  }

  /**
   * Used internally to convert an SSID into an acceptable format to create a WifiConfiguration.
   *
   * @param ssid The SSID to format
   *
   * @return String - The formatted SSID value
   */
  static String convertSSIDForConfig(@NonNull final String ssid) {
    return String.format("%s%s%s", Symbols.QUOTE, ssid, Symbols.QUOTE);
  }

  /**
   * Checks if a string is empty.
   *
   * @param string The string to check.
   *
   * @return boolean - Whether the string is empty (true if null or 0 characters)
   */
  public static boolean isEmpty(@Nullable final String string) {
    return string == null || string.trim().length() == 0;
  }

  /**
   * Checks if a string is not empty.
   *
   * @param string The string to check.
   *
   * @see #isEmpty(String)
   *
   * @return boolean - Returns the opposite of #isEmpty
   */
  public static boolean isNotEmpty(@Nullable final String string) {
    return !isEmpty(string);
  }
}
