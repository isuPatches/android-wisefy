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
package com.isupatches.android.wisefy.sample.scaffolding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

internal abstract class BaseViewModelFactory<VIEW_MODEL : ViewModel>(
    private val supportedClass: KClass<VIEW_MODEL>,
    private val vmProvider: () -> VIEW_MODEL
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(supportedClass.java)) {
            @Suppress("UNCHECKED_CAST")
            return vmProvider() as T
        } else {
            throw IllegalArgumentException(
                "${modelClass.simpleName} is an unknown type of view model. " +
                    "Expected: ${supportedClass.java.simpleName}"
            )
        }
    }
}
