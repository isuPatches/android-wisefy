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
 * Constants for different network security types.
 *
 * @author Patches
 */
public final class Capabilities {

  public static final String EAP = "EAP";
  public static final String PSK = "PSK";
  public static final String WEP = "WEP";
  public static final String WPA = "WPA";
  public static final String WPA2 = "WPA2";

  private Capabilities() {
    // No-op
  }

  /**
   * Interface to avoid magic numbers when using capabilities.
   */
  @Retention(RetentionPolicy.SOURCE)
  @StringDef({
          EAP,
          PSK,
          WEP,
          WPA,
          WPA2
  })
  public @interface Capability {}
}
