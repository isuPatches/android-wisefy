package com.isupatches.wisefysample.internal.di

import android.content.Context

import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefysample.DebugMainApplication
import com.isupatches.wisefysample.MainApplication
import com.isupatches.wisefysample.TestRxSchedulersProvider
import com.isupatches.wisefysample.internal.preferences.AddNetworkStore
import com.isupatches.wisefysample.internal.preferences.RemoveNetworkStore
import com.isupatches.wisefysample.internal.preferences.SearchStore
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.internal.util.RxSchedulersProvider
import com.isupatches.wisefysample.ui.add.AddNetworkMvp
import com.isupatches.wisefysample.ui.misc.MiscMvp
import com.isupatches.wisefysample.ui.remove.RemoveNetworkMvp
import com.isupatches.wisefysample.ui.search.SearchMvp

import dagger.BindsInstance

internal interface TestMainApplicationComponentBuilder<THIS : TestMainApplicationComponentBuilder<THIS>> {
    @BindsInstance fun application(app: MainApplication): THIS
    @BindsInstance fun context(context: Context): THIS
    @BindsInstance fun wiseFy(wiseFy: WiseFyPublicApi): THIS
    @BindsInstance fun permissionUtil(permissionUtil: PermissionUtil): THIS
    @BindsInstance fun addNetworkPresenter(impl: AddNetworkMvp.Presenter): THIS
    @BindsInstance fun addNetworkStore(impl: AddNetworkStore): THIS
    @BindsInstance fun removeNetworkPresenter(impl: RemoveNetworkMvp.Presenter): THIS
    @BindsInstance fun removeNetworkStore(impl: RemoveNetworkStore): THIS
    @BindsInstance fun miscPresenter(impl: MiscMvp.Presenter): THIS
    @BindsInstance fun searchPresenter(impl: SearchMvp.Presenter): THIS
    @BindsInstance fun searchStore(impl: SearchStore): THIS
    @BindsInstance fun rxSchedulersProv(prov: RxSchedulersProvider): THIS
}

internal fun <T : TestMainApplicationComponentBuilder<*>> T.addCommonComponents(
    app: DebugMainApplication,
    wiseFy: WiseFyPublicApi,
    permissionUtil: PermissionUtil,
    addNetworkPresenter: AddNetworkMvp.Presenter,
    addNetworkStore: AddNetworkStore,
    removeNetworkPresenter: RemoveNetworkMvp.Presenter,
    removeNetworkStore: RemoveNetworkStore,
    miscPresenter: MiscMvp.Presenter,
    searchPresenter: SearchMvp.Presenter,
    searchStore: SearchStore,
    rxSchedulersProvider: RxSchedulersProvider = TestRxSchedulersProvider()
): T = apply {
    application(app)
    context(app)
    wiseFy(wiseFy)
    permissionUtil(permissionUtil)
    addNetworkPresenter(addNetworkPresenter)
    addNetworkStore(addNetworkStore)
    removeNetworkPresenter(removeNetworkPresenter)
    removeNetworkStore(removeNetworkStore)
    miscPresenter(miscPresenter)
    searchPresenter(searchPresenter)
    searchStore(searchStore)
    rxSchedulersProv(rxSchedulersProvider)
}
