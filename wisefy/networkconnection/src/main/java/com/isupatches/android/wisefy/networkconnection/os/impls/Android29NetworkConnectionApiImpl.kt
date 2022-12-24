/*
 * Copyright 2022 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.networkconnection.os.impls

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.isupatches.android.wisefy.core.logging.WisefyLogger
import com.isupatches.android.wisefy.networkconnection.os.apis.Android29NetworkConnectionApi

/**
 * An Android 29 specific implementation for connecting to or disconnecting from a network through the Android OS.
 *
 * @param logger The WisefyLogger instance to use
 *
 * @see Android29NetworkConnectionApi
 *
 * @author Patches Klinefelter
 * @since 03/2022
 */
@RequiresApi(Build.VERSION_CODES.Q)
internal class Android29NetworkConnectionApiImpl(
    private val logger: WisefyLogger
) : Android29NetworkConnectionApi {

    companion object {
        private const val LOG_TAG = "Android29NetworkConnectionApiImpl"
    }

    override fun openInternetConnectivityPanel(context: Context) {
        logger.d(LOG_TAG, "Opening internet connectivity panel")
        context.startActivity(
            Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}
