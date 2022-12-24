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
package com.isupatches.android.wisefy.sample.ui.components.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.isupatches.android.wisefy.sample.R

internal sealed class WisefySampleBottomNavigationItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val stringResId: Int
) {

    object Add : WisefySampleBottomNavigationItem(
        route = WisefySampleNavGraph.Main.Add.route,
        icon = R.drawable.ic_add_circle,
        stringResId = R.string.add
    )

    object Remove : WisefySampleBottomNavigationItem(
        route = WisefySampleNavGraph.Main.Remove.route,
        icon = R.drawable.ic_remove_circle,
        stringResId = R.string.remove
    )

    object Home : WisefySampleBottomNavigationItem(
        route = WisefySampleNavGraph.Main.Home.route,
        icon = R.drawable.ic_home,
        stringResId = R.string.home
    )

    object Misc : WisefySampleBottomNavigationItem(
        route = WisefySampleNavGraph.Main.Misc.route,
        icon = R.drawable.ic_apps,
        stringResId = R.string.misc
    )

    object Search : WisefySampleBottomNavigationItem(
        route = WisefySampleNavGraph.Main.Search.route,
        icon = R.drawable.ic_search,
        stringResId = R.string.search
    )
}
