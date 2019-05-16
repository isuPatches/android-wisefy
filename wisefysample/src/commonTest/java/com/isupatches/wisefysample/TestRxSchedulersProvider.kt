package com.isupatches.wisefysample

import com.isupatches.wisefysample.internal.util.RxSchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxSchedulersProvider(mainThread: Scheduler = Schedulers.trampoline()) : RxSchedulersProvider() {
    override val main: Scheduler = mainThread
    override val io: Scheduler = Schedulers.trampoline()
}
