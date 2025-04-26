/*
 * Copyright (c) 2024. Patches Barrett
 *
 * Last modified: September 21, 2024
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
package com.isupatches.android.wisefy.core.assertions

/**
 * An assertion class that will allow Wisefy to assert for dev feedback to know about improper implementation or use.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
interface WisefyAssertions {

    /**
     * An assertion function that will only throw if assertions are enabled.
     *
     * @param message The message for the exception to throw
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    @Throws(IllegalStateException::class)
    fun fail(message: String)
}
