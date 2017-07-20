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
import android.util.Log;
import com.isupatches.wisefy.WiseFy;
import com.isupatches.wisefy.util.LogUtil;


/**
 * A Thread to use for all WiseFy background processing
 *
 * *NOTE* Must be cleaned!! {@link WiseFy#dump()}
 *
 * @author Patches
 */
public class WiseFyHandlerThread extends HandlerThread {

    public static final String TAG = WiseFyHandlerThread.class.getSimpleName();

    private boolean mLoggingEnabled;

    public WiseFyHandlerThread(String name, boolean loggingEnabled) {
        super(name);
        this.mLoggingEnabled = loggingEnabled;
    }

    @Override
    public synchronized void start() {
        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, "WiseFyHandlerThread starting");
        }
        super.start();
    }

    @Override
    public boolean quit() {
        if (LogUtil.isLoggable(TAG, Log.DEBUG, mLoggingEnabled)) {
            Log.d(TAG, "quit called on WiseFyHandlerThread");
        }
        return super.quit();
    }
}
