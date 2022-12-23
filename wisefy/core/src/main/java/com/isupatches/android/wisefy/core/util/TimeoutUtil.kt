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
package com.isupatches.android.wisefy.core.util

private const val BASE_DELAY_IN_MS = 1000L

fun withTimeout(timeoutInMillis: Int, block: () -> Boolean): Boolean {
    var currentTime: Long
    val endTime = System.currentTimeMillis() + timeoutInMillis
    do {
        if (block()) {
            return true
        }
        sleep()
        currentTime = System.currentTimeMillis()
    } while (currentTime < endTime)
    return false
}

suspend fun withTimeoutAsync(timeoutInMillis: Int, block: suspend () -> Boolean): Boolean {
    var currentTime: Long
    val endTime = System.currentTimeMillis() + timeoutInMillis
    do {
        if (block()) {
            return true
        }
        sleep()
        currentTime = System.currentTimeMillis()
    } while (currentTime < endTime)
    return false
}

private fun sleep(timeToSleepInMillis: Long = BASE_DELAY_IN_MS) {
    try {
        Thread.sleep(timeToSleepInMillis)
    } catch (ie: InterruptedException) {
        // Do nothing
    }
}
