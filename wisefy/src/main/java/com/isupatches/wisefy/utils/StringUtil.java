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

import android.os.Build;
import android.support.annotation.Nullable;

import com.isupatches.wisefy.annotations.Internal;
import com.isupatches.wisefy.constants.Symbols;

/**
 * A helper class to manipulate the string value for SSIDs.
 *
 * @author Patches
 */
@Internal
public final class StringUtil {

  /**
   * Private constructor.
   */
  private StringUtil() {
  }

  /**
   * Used internally to convert an SSID into a format for KitKat and below if needed.
   *
   * @param ssid The SSID to alter
   *
   * @return String - The converted SSID value
   */
  static String convertSSIDForConfig(final String ssid) {
    // On devices with version Kitkat and below, We need to send SSID name
    // with double quotes. On devices with version Lollipop, We need to send
    // SSID name without double quotes
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      return ssid;
    } else {
      return Symbols.QUOTE + ssid + Symbols.QUOTE;
    }
  }

  public static boolean isEmpty(@Nullable final String string) {
    return string == null || string.trim().length() == 0;
  }
}
