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

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.features.add.AddScreen
import com.isupatches.android.wisefy.sample.features.misc.MiscScreen
import com.isupatches.android.wisefy.sample.features.remove.RemoveScreen
import com.isupatches.android.wisefy.sample.features.search.SearchScreen
import com.isupatches.android.wisefy.sample.main.HomeScreen

@Composable
internal fun WisefySampleNavHost(navController: NavHostController, wisefy: WisefyApi) {
    NavHost(navController = navController, startDestination = WisefySampleNavigationItem.Home.route) {
        composable(WisefySampleNavigationItem.Add.route) {
            AddScreen()
        }
        composable(WisefySampleNavigationItem.Remove.route) {
            RemoveScreen()
        }
        composable(WisefySampleNavigationItem.Home.route) {
            HomeScreen()
        }
        composable(WisefySampleNavigationItem.Misc.route) {
            MiscScreen(wisefy)
        }
        composable(WisefySampleNavigationItem.Search.route) {
            SearchScreen()
        }
    }
}
