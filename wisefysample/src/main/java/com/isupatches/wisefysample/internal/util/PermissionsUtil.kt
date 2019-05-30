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
