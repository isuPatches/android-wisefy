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
package com.isupatches.android.wisefy.core.util

private const val BASE_DELAY_IN_MS = 1000L

/**
 * A convenience function that will wait for a given time until the provided function returns true.
 *
 * Usage example:
 *   withTimeout(timeoutInMillis) {
 *       filteredAccessPoints = filterAccessPoints(filterDuplicates, matcher)
 *       // Stops loop at end of timeout or if we have a non-empty list of access points
 *       filteredAccessPoints.isNotEmpty()
 *   }
 *
 * @param timeoutInMillis The allotted time for the code to execute before aborting
 * @param block A code block that should return true to exit the loop, otherwise false to continue until the time
 * limit is met
 *
 * @return Boolean - True if the condition was met before the timeout was reached, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
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

/**
 * A convenience function that will wait for a given time until the provided function returns true.
 *
 * Usage example:
 *   withTimeoutAsync(timeoutInMillis) {
 *       // Stops loop at the end of the timeout or when connected to network with a matching SSID
 *       isCurrentNetworkConnectedBySSID(ssid)
 *   }
 *
 * @param timeoutInMillis The allotted time for the code to execute before aborting
 * @param block A suspendable code block that should return true to exit the loop, otherwise false to continue until
 * the time limit is met
 *
 * @return Boolean - True if the condition was met before the timeout was reached, otherwise false
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
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
