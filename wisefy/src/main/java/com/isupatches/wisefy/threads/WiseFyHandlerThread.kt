/*
 * Copyright 2018 Patches Klinefelter
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
package com.isupatches.wisefy.threads

import android.os.HandlerThread

/**
 * A Thread to use for all WiseFy background processing.
 *
 * *NOTE* Must be cleaned!! [com.isupatches.wisefy.WiseFy.dump]
 *
 * Updates
 * - 07/26/2021: Update variable from TAG to NAME
 *
 * @author Patches
 * @since 3.0
 */
internal class WiseFyHandlerThread(name: String) : HandlerThread(name) {

    companion object {
        internal val NAME = WiseFyHandlerThread::class.java.simpleName
    }
}
