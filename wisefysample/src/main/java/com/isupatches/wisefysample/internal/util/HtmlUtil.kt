package com.isupatches.wisefysample.internal.util

import android.os.Build
import android.text.Html
import android.text.Spanned

@Suppress("DEPRECATION")
internal fun String.asHtmlSpanned(): Spanned =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(this)
    }
