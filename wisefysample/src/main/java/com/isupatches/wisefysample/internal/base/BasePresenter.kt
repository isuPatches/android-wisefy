package com.isupatches.wisefysample.internal.base

import android.util.Log

import com.isupatches.wisefy.constants.WiseFyCode
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider

internal abstract class BasePresenter<V : BaseMvp.View> constructor(
    private val rxSchedulersProvider: RxSchedulersProvider
): BaseMvp.Presenter<V> {

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
