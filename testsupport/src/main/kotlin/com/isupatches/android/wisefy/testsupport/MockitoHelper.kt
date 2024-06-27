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
package com.isupatches.android.wisefy.testsupport

import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq

/**
 * A work around for Mockito `any()` crashing with an NPE.
 *
 * https://stackoverflow.com/questions/36272751/mockito-nullpointerexception-while-using-any
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun <T> anyNonNull(): T = any()

/**
 * A work around for Mockito `eq()` crashing with an NPE.
 *
 * https://stackoverflow.com/questions/36272751/mockito-nullpointerexception-while-using-any
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
fun <T> eqNonNull(obj: T): T = eq(obj)
