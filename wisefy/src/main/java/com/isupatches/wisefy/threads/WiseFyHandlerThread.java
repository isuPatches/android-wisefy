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
package com.isupatches.wisefy.threads;

import android.os.HandlerThread;
import android.support.annotation.NonNull;

import com.isupatches.wisefy.WiseFy;

/**
 * A Thread to use for all WiseFy background processing.
 *
 * <p>*NOTE* Must be cleaned!! {@link WiseFy#dump()}</p>
 *
 * @author Patches
 */
public final class WiseFyHandlerThread extends HandlerThread {

  public static final String TAG = WiseFyHandlerThread.class.getSimpleName();

  public WiseFyHandlerThread(@NonNull final String name) {
    super(name);
  }

  @Override
  public void start() {
    super.start();
  }

  @Override
  public boolean quit() {
    return super.quit();
  }

  @Override
  public boolean quitSafely() {
    return super.quitSafely();
  }
}
