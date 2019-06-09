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

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import javax.inject.Inject

internal interface PermissionUtil {
    fun isPermissionGranted(context: Context, permission: String): Boolean

    fun shouldShowRequestPermissionRationale(
        fragment: Fragment,
        permission: String
    ): Boolean
}

internal class PermissionsUtilImpl @Inject constructor() : PermissionUtil {

    override fun isPermissionGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun shouldShowRequestPermissionRationale(
        fragment: Fragment,
        permission: String
    ): Boolean {
        return fragment.shouldShowRequestPermissionRationale(permission)
    }
}
