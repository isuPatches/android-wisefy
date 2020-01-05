/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.internal.util

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * A syntactic-sugar function used to apply arguments to a Fragment's bundle
 *
 * @param block The arguments to add to the bundle
 *
 * @return The fragment whose bundle was altered
 */
fun <T : Fragment> T.applyArguments(block: Bundle.() -> Unit): T {
    arguments = Bundle().apply(block)
    return this
}
