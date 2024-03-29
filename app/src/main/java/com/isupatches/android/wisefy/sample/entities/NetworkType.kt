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
package com.isupatches.android.wisefy.sample.entities

internal enum class NetworkType(val intVal: Int) {
    OPEN(0),
    WPA2(1),
    WPA3(2);

    companion object {

        fun of(intVal: Int): NetworkType {
            return when (intVal) {
                OPEN.intVal -> OPEN
                WPA2.intVal -> WPA2
                WPA3.intVal -> WPA3
                else -> throw IllegalArgumentException("Invalid NetworkType, intVal: $intVal")
            }
        }
    }
}
