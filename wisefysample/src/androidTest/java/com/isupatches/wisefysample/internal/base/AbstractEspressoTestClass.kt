package com.isupatches.wisefysample.internal.base

import androidx.test.platform.app.InstrumentationRegistry
import com.isupatches.wisefy.WiseFyPublicApi
import com.isupatches.wisefysample.DebugMainApplication
import com.isupatches.wisefysample.TestRxSchedulersProvider
import com.isupatches.wisefysample.internal.di.DaggerTestMainApplicationComponent
import com.isupatches.wisefysample.internal.di.addCommonComponents
import com.isupatches.wisefysample.internal.preferences.InMemoryAddNetworkStore
import com.isupatches.wisefysample.internal.preferences.InMemoryRemoveNetworkStore
import com.isupatches.wisefysample.internal.preferences.InMemorySearchStore
import com.isupatches.wisefysample.internal.util.PermissionUtil
import com.isupatches.wisefysample.internal.util.SdkUtil
import com.isupatches.wisefysample.ui.add.AddNetworkModel
import com.isupatches.wisefysample.ui.add.AddNetworkMvp
import com.isupatches.wisefysample.ui.add.AddNetworkPresenter
import com.isupatches.wisefysample.ui.misc.MiscModel
import com.isupatches.wisefysample.ui.misc.MiscMvp
import com.isupatches.wisefysample.ui.misc.MiscPresenter
import com.isupatches.wisefysample.ui.remove.RemoveNetworkModel
import com.isupatches.wisefysample.ui.remove.RemoveNetworkMvp
import com.isupatches.wisefysample.ui.remove.RemoveNetworkPresenter
import com.isupatches.wisefysample.ui.search.SearchModel
import com.isupatches.wisefysample.ui.search.SearchMvp
import com.isupatches.wisefysample.ui.search.SearchPresenter

import com.nhaarman.mockitokotlin2.mock

internal abstract class AbstractEspressoTestClass {

    protected val wiseFy: WiseFyPublicApi = mock()
    protected val permissionUtil: PermissionUtil = mock()
    protected val sdkUtil: SdkUtil = mock()

    private val addNetworkPresenter: AddNetworkMvp.Presenter = AddNetworkPresenter(
        model = AddNetworkModel(wiseFy = wiseFy),
        rxSchedulersProvider = TestRxSchedulersProvider()
    )
    protected val addNetworkStore = InMemoryAddNetworkStore()

    private val removeNetworkPresenter: RemoveNetworkMvp.Presenter = RemoveNetworkPresenter(
        model = RemoveNetworkModel(wiseFy = wiseFy),
        rxSchedulersProvider = TestRxSchedulersProvider()
    )
    protected val removeNetworkStore = InMemoryRemoveNetworkStore()

    private val miscPresenter: MiscMvp.Presenter = MiscPresenter(
        model = MiscModel(wiseFy = wiseFy),
        rxSchedulersProvider = TestRxSchedulersProvider()
    )
    private val searchPresenter: SearchMvp.Presenter = SearchPresenter(
        model = SearchModel(wiseFy = wiseFy),
        rxSchedulersProvider = TestRxSchedulersProvider()
    )
    protected val searchStore = InMemorySearchStore()

    open fun setUp() {
        @SuppressWarnings("UnsafeCast")
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as DebugMainApplication
        val mainComponent = DaggerTestMainApplicationComponent.builder()
            .addCommonComponents(
                app = app,
                wiseFy = wiseFy,
                permissionUtil = permissionUtil,
                addNetworkPresenter = addNetworkPresenter,
                addNetworkStore = addNetworkStore,
                removeNetworkPresenter = removeNetworkPresenter,
                removeNetworkStore = removeNetworkStore,
                miscPresenter = miscPresenter,
                searchPresenter = searchPresenter,
                searchStore = searchStore,
                sdkUtil = sdkUtil
            )
            .build()
        app.setTestComponent(mainComponent)
    }
}
