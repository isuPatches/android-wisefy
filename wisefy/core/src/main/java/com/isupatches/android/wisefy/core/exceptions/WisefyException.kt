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
package com.isupatches.android.wisefy.core.exceptions

/**
 * A Wisefy specific throwable to help clients narrow their catch clauses down to a specific type if needed.
 * This also standardizes how Wisefy returns errors from the async operations.
 *
 * @param message The optional message for the exception
 * @param throwable The optional cause of the exception
 *
 * @author Patches Klinefelter
 * @since 11/2022, version 5.0.0
 */
class WisefyException(message: String?, throwable: Throwable?) : Throwable(message, throwable)
