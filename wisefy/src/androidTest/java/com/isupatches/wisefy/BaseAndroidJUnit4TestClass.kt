package com.isupatches.wisefy

import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.support.test.InstrumentationRegistry
import org.junit.After
import org.mockito.Mockito
import org.mockito.Mockito.mock

/**
 * A common test class to extend.
 *
 * @author Patches
 */
internal open class BaseAndroidJUnit4TestClass {

    protected val wiseFy: WiseFy
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

        wiseFy = WiseFy.Brains(InstrumentationRegistry.getTargetContext())
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

        nullCallbackUtil = NullCallbackUtil(wiseFy)

        verificationUtil = VerificationUtil(mockConnectivityManager, mockWifiManager)
    }

    @After fun tearDown() {
        wiseFy.dump()
    }

    /*
   * HELPERS
   */

    /**
     * Checks to see if the device has Lollipop or higher.
     *
     * @return boolean - True if device is at least Lollipop
     *
     * @see Build.VERSION_CODES
     */
    protected fun preLollipop(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP

    protected fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}
