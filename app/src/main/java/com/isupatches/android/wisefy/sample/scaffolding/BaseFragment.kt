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
package com.isupatches.android.wisefy.sample.scaffolding

import androidx.annotation.StringRes
import com.isupatches.android.wisefy.sample.R
import com.isupatches.android.wisefy.sample.dialogs.FullScreenNoticeDialogFragment
import com.isupatches.android.wisefy.sample.dialogs.NoticeDialogFragment
import com.isupatches.android.wisefy.sample.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal abstract class BaseFragment : BaseView() {

    @Inject
    lateinit var permissionUtil: PermissionUtil

    private fun isActivityInvalid(): Boolean = activity == null || (activity?.isFinishing == true)

    protected fun displayInfo(@StringRes infoMessageResId: Int, @StringRes infoTitleResId: Int = R.string.info) {
        showDialogNoDuplicates(
            tag = NoticeDialogFragment.TAG,
            dialog = NoticeDialogFragment.newInstance(
                title = getString(infoTitleResId),
                message = getString(infoMessageResId)
            )
        )
    }

    protected fun displayInfo(infoMessage: String, @StringRes infoTitleResId: Int) {
        showDialogNoDuplicates(
            tag = NoticeDialogFragment.TAG,
            dialog = NoticeDialogFragment.newInstance(
                title = getString(infoTitleResId),
                message = infoMessage
            )
        )
    }

    protected fun displayInfoFullScreen(infoMessage: String, @StringRes infoTitleResId: Int) {
        showDialogNoDuplicates(
            tag = FullScreenNoticeDialogFragment.TAG,
            dialog = FullScreenNoticeDialogFragment.newInstance(
                title = getString(infoTitleResId),
                message = infoMessage
            )
        )
    }

    protected fun displayPermissionErrorDialog(@StringRes permissionErrorMessageResId: Int) {
        showDialogNoDuplicates(
            tag = NoticeDialogFragment.TAG,
            dialog = NoticeDialogFragment.newInstance(
                title = getString(R.string.permission_error),
                message = getString(permissionErrorMessageResId)
            )
        )
    }

    protected fun displayWisefyAsyncErrorDialog(throwable: Throwable) {
        showDialogNoDuplicates(
            tag = NoticeDialogFragment.TAG,
            dialog = NoticeDialogFragment.newInstance(
                title = getString(R.string.wisefy_async_error),
                message = getString(R.string.wisefy_async_error_descriptions_args, throwable.message)
            )
        )
    }

    private fun <T : BaseDialogFragment> showDialogNoDuplicates(tag: String, dialog: T) {
        if (isActivityInvalid()) return
        dialog.showNoDuplicates(childFragmentManager, tag)
    }
}
