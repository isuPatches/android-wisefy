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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.features.add.AddNetworkScreen
import com.isupatches.android.wisefy.sample.features.misc.MiscScreen
import com.isupatches.android.wisefy.sample.features.misc.nearbyaccesspoints.NearbyAccessPointsScreen
import com.isupatches.android.wisefy.sample.features.misc.signal.SignalScreen
import com.isupatches.android.wisefy.sample.features.remove.RemoveNetworkScreen
import com.isupatches.android.wisefy.sample.features.search.SearchScreen
import com.isupatches.android.wisefy.sample.main.HomeScreen
import com.isupatches.android.wisefy.sample.util.SdkUtil

@Composable
internal fun WisefySampleNavHost(
    navController: NavHostController,
    wisefy: WisefyApi,
    sdkUtil: SdkUtil,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = WisefySampleNavGraph.Main.Home.route,
        modifier = Modifier.padding(paddingValues = padding)
    ) {
        composable(WisefySampleNavGraph.Main.Add.route) {
            AddNetworkScreen(wisefy = wisefy, sdkUtil = sdkUtil)
        }
        composable(WisefySampleNavGraph.Main.Remove.route) {
            RemoveNetworkScreen(wisefy = wisefy)
        }
        composable(WisefySampleNavGraph.Main.Home.route) {
            HomeScreen()
        }
        composable(WisefySampleNavGraph.Main.Misc.route) {
            MiscScreen(wisefy = wisefy, sdkUtil = sdkUtil, navController = navController)
        }
        composable(WisefySampleNavGraph.Main.Search.route) {
            SearchScreen(wisefy = wisefy)
        }
        composable(WisefySampleNavGraph.Misc.Signal.route) {
            SignalScreen(wisefy = wisefy, sdkUtil = sdkUtil)
        }
        composable(WisefySampleNavGraph.Misc.NearbyAccessPoints.route) {
            NearbyAccessPointsScreen(wisefy = wisefy)
        }
    }
}
