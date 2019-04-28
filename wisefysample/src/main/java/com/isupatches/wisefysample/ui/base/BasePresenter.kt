package com.isupatches.wisefysample.ui.base

import android.util.Log

import com.isupatches.wisefy.constants.WiseFyCode

import io.reactivex.android.schedulers.AndroidSchedulers

abstract class BasePresenter<V : BaseMvp.View> : BaseMvp.Presenter<V> {

    private val TAG = BasePresenter::class.java.simpleName

    private var view: V? = null

    private val mainThread  = AndroidSchedulers.mainThread()

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
            mainThread.scheduleDirect {
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

    @Suppress("unused")
    protected interface ViewCommand<V : BaseMvp.View> {
        fun command(view: V?)
    }
}
