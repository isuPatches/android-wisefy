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
package com.isupatches.android.wisefy.sample.ui.components.navigation

import com.isupatches.android.wisefy.sample.R

internal sealed class WisefySampleNavigationItem(val route: String, val icon: Int, val title: Int) {
    object Add : WisefySampleNavigationItem("add", R.drawable.ic_add_circle, R.string.add)
    object Remove : WisefySampleNavigationItem("remove", R.drawable.ic_remove_circle, R.string.remove)
    object Home : WisefySampleNavigationItem("home", R.drawable.ic_home, R.string.home)
    object Misc : WisefySampleNavigationItem("misc", R.drawable.ic_apps, R.string.misc)
    object Search : WisefySampleNavigationItem("search", R.drawable.ic_search, R.string.search)
}
