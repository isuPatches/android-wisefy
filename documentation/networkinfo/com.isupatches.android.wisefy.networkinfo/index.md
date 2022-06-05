//[networkinfo](../../index.md)/[com.isupatches.android.wisefy.networkinfo](index.md)

# Package com.isupatches.android.wisefy.networkinfo

## Types

| Name | Summary |
|---|---|
| [NetworkInfoApi](-network-info-api/index.md) | [androidJvm]<br>interface [NetworkInfoApi](-network-info-api/index.md)<br>A set of synchronous APIs for getting information about a network, the device's current network, and the device's IP. |
| [NetworkInfoApiAsync](-network-info-api-async/index.md) | [androidJvm]<br>interface [NetworkInfoApiAsync](-network-info-api-async/index.md)<br>A set of asynchronous APIs for getting information about a network, the device's current network, and the device's IP. |
| [NetworkInfoDelegate](-network-info-delegate/index.md) | [androidJvm]<br>interface [NetworkInfoDelegate](-network-info-delegate/index.md) : [NetworkInfoApi](-network-info-api/index.md), [NetworkInfoApiAsync](-network-info-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs for getting information about a network, the device's current network, and the device's IP. |
| [WisefyNetworkInfoDelegate](-wisefy-network-info-delegate/index.md) | [androidJvm]<br>class [WisefyNetworkInfoDelegate](-wisefy-network-info-delegate/index.md)(coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [NetworkInfoDelegate](-network-info-delegate/index.md)<br>An internal Wisefy delegate for getting information about a network, the device's current network, and the device's IP through the Android OS. |
