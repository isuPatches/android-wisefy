//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[WisefyNetworkInfoDelegate](index.md)/[WisefyNetworkInfoDelegate](-wisefy-network-info-delegate.md)

# WisefyNetworkInfoDelegate

[androidJvm]\
fun [WisefyNetworkInfoDelegate](-wisefy-network-info-delegate.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; [NetworkConnectionStatus](../../../../core/core/com.isupatches.android.wisefy.core.entities/-network-connection-status/index.md)?, coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkInfoApi](../-network-info-api/index.md) = DefaultNetworkInfoAdapter(
        connectivityManager = connectivityManager,
        wifiManager = wifiManager,
        sdkUtil = sdkUtil,
        logger = logger,
        networkConnectionStatusProvider = networkConnectionStatusProvider
    ))

#### Parameters

androidJvm

| | |
|---|---|
| connectivityManager | The ConnectivityManager instance to use |
| logger | The [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) instance to use |
| sdkUtil | The [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) instance to use |
| wifiManager | The WifiManager instance to use |
| networkConnectionStatusProvider | The on-demand way to retrieve the current network connection status |
