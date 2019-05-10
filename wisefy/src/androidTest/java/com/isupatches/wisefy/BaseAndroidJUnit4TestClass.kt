package com.isupatches.wisefy

import android.net.ConnectivityManager
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import androidx.test.platform.app.InstrumentationRegistry

import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.connection.WiseFyConnection
import com.isupatches.wisefy.search.WiseFySearch

import org.junit.After

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * A common test class to extend.
 *
 * @author Patches
 */
internal open class BaseAndroidJUnit4TestClass {

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

        wisefy = WiseFy.Brains(InstrumentationRegistry.getInstrumentation().targetContext)
            .customConnectivityManager(mockConnectivityManager)
            .customWifiManager(mockWifiManager)
            .customWiseFyConnection(mockWiseFyConnection)
            .customWiseFyPrechecks(mockWiseFyPrechecks)
            .customWiseFySearch(mockWiseFySearch)
            .logging(true)
            .getSmarts()

        mockNetworkUtil = MockNetworkUtil(mockConnectivityManager, mockWifiManager)

        mockWiseFyConnectionUtil = MockWiseFyConnectionUtil(mockWiseFyConnection)
        mockWiseFyPrechecksUtil = MockWiseFyPrechecksUtil(mockWiseFyPrechecks)
        mockWiseFySearchUtil = MockWiseFySearchUtil(mockWiseFySearch)

        nullCallbackUtil = NullCallbackUtil(wisefy)

        verificationUtil = VerificationUtil(mockConnectivityManager, mockWifiManager)
    }

    @After fun tearDown() {
        wisefy.dump()
    }

    /*
     * HELPERS
     */

    protected fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

    protected fun verifyNetworkAdded(mockCallbacks: AddNetworkCallbacks) {
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkAdded(anyInt(), any(WifiConfiguration::class.java))
    }
}
