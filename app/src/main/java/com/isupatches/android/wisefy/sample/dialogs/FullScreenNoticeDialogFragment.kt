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
package com.isupatches.android.wisefy.sample.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.isupatches.android.viewglu.paste
import com.isupatches.android.wisefy.sample.databinding.DialogBaseFullscreenBinding
import com.isupatches.android.wisefy.sample.util.applyArguments

internal class FullScreenNoticeDialogFragment : BaseNoticeDialogFragment() {

    private var binding: DialogBaseFullscreenBinding by paste()

    private val dialogTitle: String by lazy {
        arguments?.getString(EXTRA_DIALOG_TITLE) ?: ""
    }

    private val dialogMessage: String by lazy {
        arguments?.getString(EXTRA_DIALOG_MESSAGE) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogBaseFullscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialogTitleTxt.text = dialogTitle
        binding.dialogMessageTxt.text = dialogMessage
        binding.okBtn.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        val TAG: String = FullScreenNoticeDialogFragment::class.java.simpleName

        fun newInstance(title: String, message: String): FullScreenNoticeDialogFragment {
            return FullScreenNoticeDialogFragment().applyArguments {
                putString(EXTRA_DIALOG_TITLE, title)
                putString(EXTRA_DIALOG_MESSAGE, message)
            }
        }
    }
}
