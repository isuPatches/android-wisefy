package com.isupatches.wisefysample.ui.dialogs

import android.os.Bundle
import android.view.View

import com.isupatches.wisefysample.R
import com.isupatches.wisefysample.internal.base.BaseDialogFragment
import com.isupatches.wisefysample.internal.util.applyArguments

import kotlinx.android.synthetic.main.dialog_base.dialogMessageTxt
import kotlinx.android.synthetic.main.dialog_base.dialogTitleTxt
import kotlinx.android.synthetic.main.dialog_base.okBtn

internal class NoticeDialogFragment : BaseDialogFragment() {

    override val layoutRes = R.layout.dialog_base

    private val dialogTitle by lazy {
        arguments!!.getString(EXTRA_DIALOG_TITLE)
    }

    private val dialogMessage by lazy {
        arguments!!.getString(EXTRA_DIALOG_MESSAGE)
    }

    companion object {
        val TAG: String = BaseDialogFragment::class.java.simpleName

        private const val EXTRA_DIALOG_TITLE = "dialog title"
        private const val EXTRA_DIALOG_MESSAGE = "dialog message"

        fun newInstance(title: String, message: String) =
            NoticeDialogFragment().applyArguments {
                putString(EXTRA_DIALOG_TITLE, title)
                putString(EXTRA_DIALOG_MESSAGE, message)
            }
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
