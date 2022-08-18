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
package com.isupatches.android.wisefy.core.assertions

/**
 * An assertion class that will allow Wisefy to assert for dev feedback to know about improper implementation or use.
 *
 * *Note* This should be used in cases such as debug builds and for cases that are recoverable or less noticeable from
 * the end user perspective.
 *
 * @param throwOnAssertions If assertions will throw an IllegalArgumentException when using Wisefy.  This value will
 *  typically be something like BuildConfig.DEBUG.
 *
 * @author Patches Klinefelter
 * @since 07/2022, version 5.0.0
 */
class WisefyAssertions(private val throwOnAssertions: Boolean) {

    /**
     * An assertion function that will only throw if assertions are enabled.
     *
     * @param message The message for the exception to throw
     *
     * @author Patches Klinefelter
     * @since 07/2022, version 5.0.0
     */
    fun fail(message: String) {
        if (throwOnAssertions) {
            error(message)
        }
    }
}
