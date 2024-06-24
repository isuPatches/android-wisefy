//[networkconnection](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionDelegate](index.md)/[WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate.md)

# WisefyNetworkConnectionDelegate

[androidJvm]\
constructor(assertions: WisefyAssertions, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkConnectionApi](../-network-connection-api/index.md) = if (sdkUtil.isAtLeastQ()) {
        Android29NetworkConnectionAdapter(
            logger,
            assertions,
        )
    } else {
        DefaultNetworkConnectionAdapter(
            connectivityManager,
            wifiManager,
            logger,
            sdkUtil,
            networkConnectionStatusProvider,
            assertions,
        )
    })

#### Parameters

androidJvm

| | |
|---|---|
| assertions | The WisefyAssertions instance to use |
| connectivityManager | The ConnectivityManager instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| networkConnectionStatusProvider | The on-demand way to retrieve the current network connection status |
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| networkConnectionMutex | The mutex for all read/write operations involving connecting, disconnecting, and getting the device's current network and connection status |
| adapter | The adapter instance to use for connecting, disconnecting, and changing networks (determined based on the Android OS level) |
