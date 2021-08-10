/*
 * Copyright 2021 Patches Klinefelter
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
package com.isupatches.android.wisefy.logging

interface WisefyLogger {
    fun i(tag: String, message: String, vararg args: Any): Int

    fun v(tag: String, message: String, vararg args: Any): Int

    fun d(tag: String, message: String, vararg args: Any): Int

    fun w(tag: String, message: String, vararg args: Any): Int

    fun e(tag: String, message: String, vararg args: Any): Int

    fun e(tag: String, throwable: Throwable, message: String, vararg args: Any): Int

    fun wtf(tag: String, message: String, vararg args: Any): Int

    fun wtf(tag: String, throwable: Throwable, message: String, vararg args: Any): Int
}