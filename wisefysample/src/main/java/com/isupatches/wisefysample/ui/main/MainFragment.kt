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
package com.isupatches.wisefysample.ui.main

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseFragment

internal class MainFragment : BaseFragment() {

    override val layoutRes = R.layout.fragment_main

    companion object {
        val TAG: String = MainFragment::class.java.simpleName

        fun newInstance() = MainFragment()
    }
}
