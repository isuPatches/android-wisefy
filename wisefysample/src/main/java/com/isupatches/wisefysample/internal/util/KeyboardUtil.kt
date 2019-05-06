package com.isupatches.wisefysample.internal.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

import com.isupatches.wisefysample.internal.base.BaseFragment

internal fun BaseFragment.hideKeyboardFrom(view: View) {
    activity?.let {
        (it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).also { inputManger ->
            inputManger.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}