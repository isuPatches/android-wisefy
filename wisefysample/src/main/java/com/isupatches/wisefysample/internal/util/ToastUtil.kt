package com.isupatches.wisefysample.internal.util

import android.widget.Toast
import com.isupatches.wisefysample.internal.base.BaseFragment

internal fun BaseFragment.displayShortToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}