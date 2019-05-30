package com.isupatches.wisefysample.internal.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

internal abstract class BaseDialogFragment : DialogFragment() {

    @get:LayoutRes abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, 0)
    }

    fun showNoDuplicates(manager: FragmentManager, tag: String) {
        if (manager.findFragmentByTag(tag) != null) return
        show(manager, tag)
    }
}
