//[wisefy](../../index.md)/[com.isupatches.android.wisefy.networkconnection.delegates](index.md)

# Package com.isupatches.android.wisefy.networkconnection.delegates

## Types

| Name | Summary |
|---|---|
| [Android29NetworkConnectionApi](-android29-network-connection-api/index.md) | [androidJvm]<br>internal interface [Android29NetworkConnectionApi](-android29-network-connection-api/index.md) |
| [Android29NetworkConnectionApiImpl](-android29-network-connection-api-impl/index.md) | [androidJvm]<br>internal class [Android29NetworkConnectionApiImpl](-android29-network-connection-api-impl/index.md)(**connectionManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?) : [ConnectivityManager.NetworkCallback](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.NetworkCallback.html), [Android29NetworkConnectionApi](-android29-network-connection-api/index.md) |
| [Android29NetworkConnectionDelegate](-android29-network-connection-delegate/index.md) | [androidJvm]<br>internal class [Android29NetworkConnectionDelegate](-android29-network-connection-delegate/index.md)(**connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?, **impl**: [Android29NetworkConnectionApi](-android29-network-connection-api/index.md)) : [NetworkConnectionApi](../com.isupatches.android.wisefy.networkconnection/-network-connection-api/index.md) |
| [LegacyNetworkConnectionApi](-legacy-network-connection-api/index.md) | [androidJvm]<br>internal interface [LegacyNetworkConnectionApi](-legacy-network-connection-api/index.md) |
| [LegacyNetworkConnectionApiImpl](-legacy-network-connection-api-impl/index.md) | [androidJvm]<br>internal class [LegacyNetworkConnectionApiImpl](-legacy-network-connection-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **networkConnectionStatusUtil**: [NetworkConnectionStatusUtil](../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-util/index.md), **savedNetworkUtil**: [SavedNetworkUtil](../com.isupatches.android.wisefy.savednetworks/-saved-network-util/index.md), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?) : [ConnectivityManager.NetworkCallback](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.NetworkCallback.html), [LegacyNetworkConnectionApi](-legacy-network-connection-api/index.md) |
| [LegacyNetworkConnectionDelegate](-legacy-network-connection-delegate/index.md) | [androidJvm]<br>internal class [LegacyNetworkConnectionDelegate](-legacy-network-connection-delegate/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **networkConnectionStatusUtil**: [NetworkConnectionStatusUtil](../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-util/index.md), **savedNetworkUtil**: [SavedNetworkUtil](../com.isupatches.android.wisefy.savednetworks/-saved-network-util/index.md), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?, **impl**: [LegacyNetworkConnectionApi](-legacy-network-connection-api/index.md)) : [NetworkConnectionApi](../com.isupatches.android.wisefy.networkconnection/-network-connection-api/index.md) |

## Properties

| Name | Summary |
|---|---|
| [LOG_TAG](-l-o-g_-t-a-g.md) | [androidJvm]<br>private const val [LOG_TAG](-l-o-g_-t-a-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [LOG_TAG](-l-o-g_-t-a-g.md) | [androidJvm]<br>private const val [LOG_TAG](-l-o-g_-t-a-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
