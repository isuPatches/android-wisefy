package com.isupatches.wisefy.internal.base

import android.net.ConnectivityManager
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.test.core.app.ApplicationProvider

import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.WiseFy
import com.isupatches.wisefy.WiseFyPrechecks
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.connection.WiseFyConnection
import com.isupatches.wisefy.internal.NullCallbackUtil
import com.isupatches.wisefy.internal.VerificationUtil
import com.isupatches.wisefy.internal.mock.MockNetworkUtil
import com.isupatches.wisefy.internal.mock.MockWiseFyConnectionUtil
import com.isupatches.wisefy.internal.mock.MockWiseFyPrechecksUtil
import com.isupatches.wisefy.internal.mock.MockWiseFySearchUtil
import com.isupatches.wisefy.search.WiseFySearch

import org.junit.After

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

internal abstract class BaseUnitTest {

    protected val wisefy: WiseFy
    protected val mockWiseFySearch: WiseFySearch

    protected val mockConnectivityManager: ConnectivityManager
    protected val mockWifiManager: WifiManager

    protected val mockNetworkUtil: MockNetworkUtil
    protected val mockWiseFyConnectionUtil: MockWiseFyConnectionUtil
    protected val mockWiseFyPrechecksUtil: MockWiseFyPrechecksUtil
    protected val mockWiseFySearchUtil: MockWiseFySearchUtil

    protected val nullCallbackUtil: NullCallbackUtil

    protected val verificationUtil: VerificationUtil

    /**
     * Constructor.
     */
    init {
        val mockWiseFyConnection = mock(WiseFyConnection::class.java)
        val mockWiseFyPrechecks = mock(WiseFyPrechecks::class.java)
        mockWiseFySearch = mock(WiseFySearch::class.java)

        mockWifiManager = mock(WifiManager::class.java)
        mockConnectivityManager = mock(ConnectivityManager::class.java)

        wisefy = WiseFy.Brains(ApplicationProvider.getApplicationContext())
                .customConnectivityManager(mockConnectivityManager)
                .customWifiManager(mockWifiManager)
                .customWiseFyConnection(mockWiseFyConnection)
                .customWiseFyPrechecks(mockWiseFyPrechecks)
                .customWiseFySearch(mockWiseFySearch)
                .logging(true)
                .getSmarts()

        wisefy.setupWiseFyThread(true)

        mockNetworkUtil = MockNetworkUtil(mockConnectivityManager, mockWifiManager)

        mockWiseFyConnectionUtil = MockWiseFyConnectionUtil(mockWiseFyConnection)
        mockWiseFyPrechecksUtil = MockWiseFyPrechecksUtil(mockWiseFyPrechecks)
        mockWiseFySearchUtil = MockWiseFySearchUtil(mockWiseFySearch)

        nullCallbackUtil = NullCallbackUtil(wisefy)

        verificationUtil = VerificationUtil(mockConnectivityManager, mockWifiManager)
    }

    @After open fun tearDown() {
        wisefy.dump()
    }

    protected fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    protected fun verifyNetworkAdded(mockCallbacks: AddNetworkCallbacks) {
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkAdded(anyInt(), any(WifiConfiguration::class.java))
    }
}