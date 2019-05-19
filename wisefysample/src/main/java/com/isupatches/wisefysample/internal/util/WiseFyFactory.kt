package com.isupatches.wisefysample.internal.util

import androidx.fragment.app.FragmentActivity

import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.WiseFyPublicApi

@Suppress("UndocumentedPublicFunction")
fun createWiseFy(activity: FragmentActivity): WiseFyPublicApi = WiseFy.Brains(activity)
        .logging(true)
        .getSmarts()
