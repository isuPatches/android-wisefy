//[networkconnectionstatus](../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus](index.md)

# Package com.isupatches.android.wisefy.networkconnectionstatus

## Types

| Name | Summary |
|---|---|
| [NetworkConnectionStatusApi](-network-connection-status-api/index.md) | [androidJvm]<br>interface [NetworkConnectionStatusApi](-network-connection-status-api/index.md)<br>A set of synchronous APIs for checking the device's connection status. |
| [NetworkConnectionStatusApiInternal](-network-connection-status-api-internal/index.md) | [androidJvm]<br>interface [NetworkConnectionStatusApiInternal](-network-connection-status-api-internal/index.md) : [NetworkConnectionStatusApi](-network-connection-status-api/index.md)<br>A set of synchronous internal APIs for attaching and detaching a network watcher. |
| [NetworkConnectionStatusDelegate](-network-connection-status-delegate/index.md) | [androidJvm]<br>interface [NetworkConnectionStatusDelegate](-network-connection-status-delegate/index.md) : [NetworkConnectionStatusApiInternal](-network-connection-status-api-internal/index.md)<br>A delegate for synchronous and asynchronous APIs to check the device's connection status and if it meets certain criteria. |
| [WisefyNetworkConnectionStatusDelegate](-wisefy-network-connection-status-delegate/index.md) | [androidJvm]<br>class [WisefyNetworkConnectionStatusDelegate](-wisefy-network-connection-status-delegate/index.md)(connectivityManager: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [NetworkConnectionStatusDelegate](-network-connection-status-delegate/index.md)<br>An internal Wisefy delegate for checking the device's connection status and if it meets certain criteria. |
