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

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Constants for commons codes returned from the WiseFy library.
 */
public final class WiseFyCodeDefs {

  public static final int MISSING_PARAMETER = -1000;
  public static final int MISSING_PREREQUISITE = -1001;
  public static final int NETWORK_ALREADY_CONFIGURED = -1002;

  private WiseFyCodeDefs() {
    // No-op
  }

  /**
   * Interface to avoid magic numbers when using WiseFy codes.
   */
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({
          MISSING_PARAMETER,
          MISSING_PREREQUISITE,
          NETWORK_ALREADY_CONFIGURED
  })
  public @interface WiseFyCodes {}
}
