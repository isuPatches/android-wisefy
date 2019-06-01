package com.isupatches.wisefysample.internal.util

import android.os.Build

import javax.inject.Inject

internal interface SdkUtil {

    fun isAtLeastLollipop(): Boolean
}

internal class SdkUtilImpl @Inject constructor() : SdkUtil {

    override fun isAtLeastLollipop() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}
