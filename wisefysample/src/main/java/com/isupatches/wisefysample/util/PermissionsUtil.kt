package com.isupatches.wisefysample.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionsUtil private constructor() {

    fun permissionNotGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED
    }

    companion object {
        val instance = PermissionsUtil()
    }
}