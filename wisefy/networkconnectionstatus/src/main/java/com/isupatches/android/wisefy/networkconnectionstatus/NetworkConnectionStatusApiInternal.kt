/*
 * Copyright 2022 Patches Klinefelter
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
package com.isupatches.android.wisefy.networkconnectionstatus

/**
 * A set of synchronous internal APIs for attaching and detaching a network watcher.
 *
 * @see NetworkConnectionStatusApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
interface NetworkConnectionStatusApiInternal : NetworkConnectionStatusApi {

    /**
     * A synchronous internal API that is used to add a network watcher.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun attachNetworkWatcher()

    /**
     * A synchronous internal API that is used to remove a network watcher.
     *
     * @author Patches Klinefelter
     * @since 03/2022
     */
    fun detachNetworkWatcher()
}
