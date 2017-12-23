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

import com.isupatches.wisefy.annotations.Internal;

/**
 * A helper class for shared wait logic.
 *
 * @author Patches
 */
@Internal
public final class SleepUtil {

  private static final SleepUtil SLEEP_UTIL = new SleepUtil();

  /**
   * Private constructor.
   */
  private SleepUtil() {
  }

  /**
   * Used internally to retrieve the instance of SleepUtil.
   *
   * @return instance of SleepUtil
   */
  @NonNull
  public static SleepUtil getInstance() {
    return SLEEP_UTIL;
  }

  /**
   * Used to wait for a given amount of time (in milliseconds).
   *
   * @param timeToSleepInMillis The number of milliseconds to sleep
   */
  public void sleep(final long timeToSleepInMillis) {
    try {
      Thread.sleep(timeToSleepInMillis);
    } catch (InterruptedException ie) {
      // Do nothing
    }
  }
}
