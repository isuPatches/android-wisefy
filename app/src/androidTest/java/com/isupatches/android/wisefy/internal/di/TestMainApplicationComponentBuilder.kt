package com.isupatches.android.wisefy.internal.di

import android.content.Context
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.android.wisefy.sample.DebugMainApplication
import com.isupatches.android.wisefy.sample.MainApplication
import com.isupatches.android.wisefy.TestRxSchedulersProvider
import com.isupatches.android.wisefy.sample.ui.add.AddNetworkStore
import com.isupatches.android.wisefy.sample.ui.remove.RemoveNetworkStore
import com.isupatches.android.wisefy.sample.ui.search.SearchStore
import com.isupatches.android.wisefy.sample.internal.util.PermissionUtil
import com.isupatches.android.wisefy.sample.internal.util.RxSchedulersProvider
import com.isupatches.android.wisefy.sample.internal.util.SdkUtil
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
    @BindsInstance fun sdkUtil(impl: SdkUtil): THIS
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
    sdkUtil: SdkUtil,
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
    sdkUtil(sdkUtil)
    rxSchedulersProv(rxSchedulersProvider)
}
