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
package com.isupatches.wisefysample.internal.base

import android.util.Log
import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider

internal abstract class BasePresenter<V : BaseMvp.View> constructor(
    private val rxSchedulersProvider: RxSchedulersProvider
) : BaseMvp.Presenter<V> {

    companion object {
        private val TAG = BasePresenter::class.java.simpleName
    }

    private var view: V? = null

    private val isViewAttached: Boolean
        get() = view != null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    protected fun getView(): V = view ?: throw ViewNotAttachedException()

    protected fun doSafelyWithView(viewCommand: (view: V) -> Unit) {
        if (isViewAttached) {
            rxSchedulersProvider.main.scheduleDirect {
                if (isViewAttached) {
                    viewCommand(view!!)
                } else {
                    Log.w(TAG, "ViewCommand was scheduled., but view is now detached!")
                }
            }
        }
    }

    override fun displayWiseFyFailure(@WiseFyCode wiseFyFailureCode: Int) {
        doSafelyWithView { view -> view.displayWiseFyFailure(wiseFyFailureCode) }
    }
}
