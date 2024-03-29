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

internal sealed class WisefySampleNavGraph(open val route: String) {

    sealed class Main(override val route: String) : WisefySampleNavGraph(route) {
        object Add : Main(route = "add")
        object Remove : Main(route = "remove")
        object Home : Main(route = "home")
        object Misc : Main(route = "misc")
        object Search : Main(route = "search")
    }

    sealed class Misc(override val route: String) : WisefySampleNavGraph(route) {
        object Signal : Misc(route = "signal")
        object NearbyAccessPoints : Misc(route = "nearbyAccessPoints")
    }
}
