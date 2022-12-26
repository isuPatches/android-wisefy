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
package com.isupatches.android.wisefy.networkconnection.entities

import android.content.Context

/**
 * A representation of a request to change the device's current network.
 *
 * @property context The context used to open the internet connectivity panel
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
data class ChangeNetworkRequest(val context: Context)