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
package com.isupatches.android.wisefy.sample.internal.scaffolding

import com.isupatches.android.wisefy.sample.internal.logging.WisefySampleLogger

internal interface Presenter<in VIEW : View> {
    fun attachView(view: VIEW)
    fun detachView()
}

private const val LOG_TAG = "BasePresenter"

internal abstract class BasePresenter<VIEW : BaseView> : Presenter<VIEW> {

    private var view: VIEW? = null

    private val isViewAttached: Boolean
        get() = view != null

    override fun attachView(view: VIEW) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    protected fun getView(): VIEW = view ?: throw ViewNotAttachedException()

    protected fun doSafelyWithView(viewCommand: (view: VIEW) -> Unit) {
        if (isViewAttached) {
//            view?.scheduleDirect {
            if (isViewAttached) {
                val view = view
                requireNotNull(view)
                viewCommand(view)
            } else {
                WisefySampleLogger.w(LOG_TAG, "ViewCommand was scheduled., but view is now detached!")
            }
//            }
        }
    }
}

private class ViewNotAttachedException : RuntimeException(
    "New view attached.  Did you forget to call attachView()?"
)
