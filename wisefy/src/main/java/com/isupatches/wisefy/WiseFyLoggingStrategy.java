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

/**
 * An interface for different logging options for implementation.
 *
 * @author Patches
 */
interface WiseFyLoggingStrategy {
  void debug(@NonNull String tag, @NonNull String message, @NonNull Object...args);

  void warn(@NonNull String tag, @NonNull String message, @NonNull Object...args);

  void error(@NonNull String tag, @NonNull String message, @NonNull Object...args);

  void error(@NonNull String tag, @NonNull Throwable throwable, @NonNull String message, @NonNull Object...args);
}
