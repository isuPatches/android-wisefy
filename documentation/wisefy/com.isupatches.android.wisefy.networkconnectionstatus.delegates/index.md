//[wisefy](../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus.delegates](index.md)

# Package com.isupatches.android.wisefy.networkconnectionstatus.delegates

## Types

| Name | Summary |
|---|---|
| [LegacyNetworkConnectionStatusApi](-legacy-network-connection-status-api/index.md) | [androidJvm]<br>internal interface [LegacyNetworkConnectionStatusApi](-legacy-network-connection-status-api/index.md) |
| [LegacyNetworkConnectionStatusApiImpl](-legacy-network-connection-status-api-impl/index.md) | [androidJvm]<br>internal class [LegacyNetworkConnectionStatusApiImpl](-legacy-network-connection-status-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **sdkUtil**: [SdkUtil](../com.isupatches.android.wisefy.util/-sdk-util/index.md), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?) : [ConnectivityManager.NetworkCallback](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.NetworkCallback.html), [LegacyNetworkConnectionStatusApi](-legacy-network-connection-status-api/index.md) |
| [LegacyNetworkConnectionStatusDelegate](-legacy-network-connection-status-delegate/index.md) | [androidJvm]<br>internal class [LegacyNetworkConnectionStatusDelegate](-legacy-network-connection-status-delegate/index.md)(**connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **sdkUtil**: [SdkUtil](../com.isupatches.android.wisefy.util/-sdk-util/index.md), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?, **impl**: [LegacyNetworkConnectionStatusApi](-legacy-network-connection-status-api/index.md)) : [NetworkConnectionStatusApi](../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/index.md) |

## Properties

| Name | Summary |
|---|---|
| [LOG_TAG](-l-o-g_-t-a-g.md) | [androidJvm]<br>private const val [LOG_TAG](-l-o-g_-t-a-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
