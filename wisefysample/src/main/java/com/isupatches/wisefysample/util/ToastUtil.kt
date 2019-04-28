package com.isupatches.wisefysample.util

import android.widget.Toast
import com.isupatches.wisefysample.ui.base.BaseFragment

fun BaseFragment.displayShortToast(message: String) {
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}