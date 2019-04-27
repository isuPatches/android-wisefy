package com.isupatches.wisefysample.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.isupatches.wisefysample.R

internal fun FragmentActivity.openFragment(activity: FragmentActivity?, fragment: Fragment, tag: String) {
    activity?.let {
        val supportFragmentManager = it.supportFragmentManager
        (0 until supportFragmentManager.backStackEntryCount).forEach {
            supportFragmentManager.popBackStack()
        }
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.fragmentContainer, fragment, tag)
            commit()
        }
    }
}

internal fun openFragmentAndAddToBackStack(activity: FragmentActivity, fragment: Fragment, tag: String) {
    with(activity.supportFragmentManager.beginTransaction()) {
        replace(R.id.fragmentContainer, fragment, tag)
        addToBackStack(tag)
        commit()
    }
}