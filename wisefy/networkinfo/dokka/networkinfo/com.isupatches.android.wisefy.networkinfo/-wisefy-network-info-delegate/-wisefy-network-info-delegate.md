//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)/[WisefyNetworkInfoDelegate](-wisefy-network-info-delegate.md)

# WisefyNetworkInfoDelegate

[androidJvm]\
constructor(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkInfoApi](../-network-info-api/index.md) = DefaultNetworkInfoAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        sdkUtil = sdkUtil,
        logger = logger,
        networkConnectionStatusProvider = networkConnectionStatusProvider,
    ))

#### Parameters

androidJvm

| | |
|---|---|
| connectivityManager | The ConnectivityManager instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| networkConnectionStatusProvider | The on-demand way to retrieve the current network connection status |
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| networkConnectionMutex | The mutex for all read/write operations involving connecting, disconnecting, and getting the device's current network and connection status |
| adapter | The adapter instance to use for getting the device's current network and connection status (determined based on the Android OS level) |
