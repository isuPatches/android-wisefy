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
package com.isupatches.wisefy.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Constants for different network types.
 */
public final class NetworkTypeDefs {

  public static final String MOBILE = "MOBILE";
  public static final String WIFI = "WIFI";

  private NetworkTypeDefs() {
    // No-op
  }

  /**
   * Interface to avoid magic numbers when using network types.
   */
  @Retention(RetentionPolicy.SOURCE)
  @StringDef({
          MOBILE,
          WIFI
  })
  public @interface NetworkTypes {}
}
