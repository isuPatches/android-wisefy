/*
 * Copyright 2019 Patches Klinefelter
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
package com.isupatches.wisefysample.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.util.applyArguments

internal class FullScreenNoticeDialogFragment : BaseNoticeDialogFragment() {

    override val layoutRes = R.layout.dialog_base_fullscreen

    companion object {
        val TAG: String = FullScreenNoticeDialogFragment::class.java.simpleName

        fun newInstance(title: String, message: String) =
            FullScreenNoticeDialogFragment().applyArguments {
                putString(EXTRA_DIALOG_TITLE, title)
                putString(EXTRA_DIALOG_MESSAGE, message)
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return super.onCreateDialog(savedInstanceState)
    }
}
