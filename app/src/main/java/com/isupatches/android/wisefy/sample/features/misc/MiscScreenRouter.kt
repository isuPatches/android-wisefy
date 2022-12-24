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
package com.isupatches.android.wisefy.sample.features.misc

import androidx.navigation.NavHostController
import com.isupatches.android.wisefy.sample.ui.components.navigation.WisefySampleNavGraph

internal interface MiscScreenRouter {
    fun openNearbyAccessPointsScreen()
    fun openSignalScreen()
}

internal class DefaultMiscScreenRouter(
    private val navController: NavHostController
) : MiscScreenRouter {

    override fun openNearbyAccessPointsScreen() {
        navController.navigate(WisefySampleNavGraph.Misc.NearbyAccessPoints.route)
    }

    override fun openSignalScreen() {
        navController.navigate(WisefySampleNavGraph.Misc.Signal.route)
    }
}
