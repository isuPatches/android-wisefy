/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.core.coroutines

import com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks
import com.isupatches.android.wisefy.core.exceptions.WisefyException
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * A function to create a standardized coroutines exception handler for async operations within the library.
 * This helps return exceptions in a standardized way for all async callbacks.
 *
 * *Notes* Converts a throwable to a [WisefyException] with the cause being the caught throwable
 *
 * @param callbacks The callback interface that implements BaseWisefyCallbacks to return exceptions to
 *
 * @see BaseWisefyCallbacks
 * @see WisefyException
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun createBaseCoroutineExceptionHandler(callbacks: BaseWisefyCallbacks?): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { _, throwable ->
        callbacks?.onWisefyAsyncFailure(WisefyException(message = null, throwable = throwable))
    }
}
