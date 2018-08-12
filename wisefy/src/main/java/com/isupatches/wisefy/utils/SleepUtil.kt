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
package com.isupatches.wisefy.utils

private const val BASE_DELAY = 1000

/**
 * To sleep the calling thread for a second.
 *
 * @see [BASE_DELAY]
 * @see [sleep]
 */
fun rest() {
    sleep(BASE_DELAY.toLong())
}

/**
 * Used to wait for a given amount of time (in milliseconds).
 *
 * @param timeToSleepInMillis The number of milliseconds to sleep
 */
private fun sleep(timeToSleepInMillis: Long) {
    try {
        Thread.sleep(timeToSleepInMillis)
    } catch (ie: InterruptedException) {
        // Do nothing
    }
}
