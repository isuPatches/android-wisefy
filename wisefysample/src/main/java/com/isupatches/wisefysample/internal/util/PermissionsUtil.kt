package com.isupatches.wisefysample.internal.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

import javax.inject.Inject

internal interface PermissionUtil {
    fun permissionNotGranted(context: Context, permission: String): Boolean
}

internal class PermissionsUtilImpl @Inject constructor() : PermissionUtil {

    override fun permissionNotGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
    }
}
