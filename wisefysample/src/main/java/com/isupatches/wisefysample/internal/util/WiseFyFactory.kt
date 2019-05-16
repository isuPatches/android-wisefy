package com.isupatches.wisefysample.internal.util

import androidx.fragment.app.FragmentActivity

import com.isupatches.wisefy.WiseFy

fun createWiseFy(activity: FragmentActivity): WiseFy = WiseFy.Brains(activity)
        .logging(true)
        .getSmarts()
