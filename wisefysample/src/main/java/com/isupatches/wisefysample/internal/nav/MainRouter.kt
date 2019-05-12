package com.isupatches.wisefysample.internal.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.isupatches.wisefysample.R

internal fun BottomNavigationView.selectItem(actionId: Int) {
    menu.findItem(actionId)?.isChecked = true
}

internal fun openFragment(activity: FragmentActivity?, fragment: Fragment, tag: String) {
    activity?.let {
        val supportFragmentManager = it.supportFragmentManager
        (0 until supportFragmentManager.backStackEntryCount).forEach { _ ->
            supportFragmentManager.popBackStack()
        }
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.fragmentContainer, fragment, tag)
            commit()
        }
    }
}
