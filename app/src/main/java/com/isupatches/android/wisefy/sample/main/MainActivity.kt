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
package com.isupatches.android.wisefy.sample.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.isupatches.android.wisefy.WisefyApi
import com.isupatches.android.wisefy.sample.ui.components.WisefySampleToolbar
import com.isupatches.android.wisefy.sample.ui.components.navigation.WisefySampleBottomNavigation
import com.isupatches.android.wisefy.sample.ui.components.navigation.WisefySampleNavGraph
import com.isupatches.android.wisefy.sample.ui.components.navigation.WisefySampleNavHost
import com.isupatches.android.wisefy.sample.ui.theme.WisefySampleTheme
import com.isupatches.android.wisefy.sample.util.SdkUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : ComponentActivity() {

    @Inject
    lateinit var wisefy: WisefyApi

    @Inject
    lateinit var sdkUtil: SdkUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wisefy.init()
        setContent {
            WisefySampleTheme {
                window?.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                MainScreenLayout(wisefy, sdkUtil)
            }
        }
    }

    override fun onDestroy() {
        wisefy.dump()
        super.onDestroy()
    }
}

@Composable
internal fun MainScreenLayout(wisefy: WisefyApi, sdkUtil: SdkUtil) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { WisefySampleToolbar() },
        content = { padding ->
            WisefySampleNavHost(navController, wisefy, sdkUtil, padding)
        },
        bottomBar = {
            val showBottomNav = when (currentRoute(navController = navController)) {
                WisefySampleNavGraph.Main.Add.route -> true
                WisefySampleNavGraph.Main.Remove.route -> true
                WisefySampleNavGraph.Main.Home.route -> true
                WisefySampleNavGraph.Main.Misc.route -> true
                WisefySampleNavGraph.Main.Search.route -> true
                else -> false
            }
            if (showBottomNav) {
                WisefySampleBottomNavigation(navController = navController)
            }
        }
    )
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
