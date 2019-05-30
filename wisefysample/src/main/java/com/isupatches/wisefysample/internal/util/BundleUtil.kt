package com.isupatches.wisefysample.internal.util

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 *
 */
fun <T : Fragment> T.applyArguments(block: Bundle.() -> Unit): T {
    arguments = Bundle().apply(block)
    return this
}
