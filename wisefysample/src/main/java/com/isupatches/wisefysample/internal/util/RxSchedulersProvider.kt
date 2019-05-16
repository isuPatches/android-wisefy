package com.isupatches.wisefysample.internal.util

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

import javax.inject.Inject

open class RxSchedulersProvider @Inject constructor() {
    open val main: Scheduler by lazy { AndroidSchedulers.mainThread() }
    open val io: Scheduler by lazy { Schedulers.io() }
}
