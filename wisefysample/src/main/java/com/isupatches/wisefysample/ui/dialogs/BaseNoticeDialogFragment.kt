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

import android.os.Bundle
import android.view.View

import com.isupatches.wisefysample.internal.base.BaseDialogFragment

import kotlinx.android.synthetic.main.dialog_base.dialogMessageTxt
import kotlinx.android.synthetic.main.dialog_base.dialogTitleTxt
import kotlinx.android.synthetic.main.dialog_base.okBtn

internal const val EXTRA_DIALOG_TITLE = "dialog title"
internal const val EXTRA_DIALOG_MESSAGE = "dialog message"

internal abstract class BaseNoticeDialogFragment : BaseDialogFragment() {

    private val dialogTitle: String by lazy {
        arguments!!.getString(EXTRA_DIALOG_TITLE)
    }

    private val dialogMessage: String by lazy {
        arguments!!.getString(EXTRA_DIALOG_MESSAGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogTitleTxt.text = dialogTitle
        dialogMessageTxt.text = dialogMessage
        okBtn.setOnClickListener {
            dismiss()
        }
    }
}
