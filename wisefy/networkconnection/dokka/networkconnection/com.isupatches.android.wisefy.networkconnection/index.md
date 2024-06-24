//[networkconnection](../../index.md)/[com.isupatches.android.wisefy.networkconnection](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [NetworkConnectionApi](-network-connection-api/index.md) | [androidJvm]<br>interface [NetworkConnectionApi](-network-connection-api/index.md)<br>A set of synchronous APIs for connecting to and disconnecting from a network. |
| [NetworkConnectionApiAsync](-network-connection-api-async/index.md) | [androidJvm]<br>interface [NetworkConnectionApiAsync](-network-connection-api-async/index.md)<br>A set of asynchronous APIs for connecting to and disconnecting from a network. |
| [NetworkConnectionDelegate](-network-connection-delegate/index.md) | [androidJvm]<br>interface [NetworkConnectionDelegate](-network-connection-delegate/index.md) : [NetworkConnectionApi](-network-connection-api/index.md), [NetworkConnectionApiAsync](-network-connection-api-async/index.md)<br>A delegate for synchronous and asynchronous APIs to connect to and disconnect from networks. |
| [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate/index.md) | [androidJvm]<br>class [WisefyNetworkConnectionDelegate](-wisefy-network-connection-delegate/index.md)(assertions: WisefyAssertions, connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), networkConnectionStatusProvider: suspend () -&gt; NetworkConnectionStatus?, coroutineDispatcherProvider: CoroutineDispatcherProvider, scope: CoroutineScope, networkConnectionMutex: Mutex, adapter: [NetworkConnectionApi](-network-connection-api/index.md) = if (sdkUtil.isAtLeastQ()) {         Android29NetworkConnectionAdapter(             logger,             assertions,         )     } else {         DefaultNetworkConnectionAdapter(             connectivityManager,             wifiManager,             logger,             sdkUtil,             networkConnectionStatusProvider,             assertions,         )     }) : [NetworkConnectionDelegate](-network-connection-delegate/index.md)<br>An internal Wisefy delegate for getting and searching for nearby access points through the Android OS. |
