//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.delegates](../index.md)/[Android29NetworkConnectionApiImpl](index.md)

# Android29NetworkConnectionApiImpl

[androidJvm]\
internal class [Android29NetworkConnectionApiImpl](index.md)(**connectionManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?) : [ConnectivityManager.NetworkCallback](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.NetworkCallback.html), [Android29NetworkConnectionApi](../-android29-network-connection-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [connectToNetwork](connect-to-network.md)(ssidToConnectTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md) |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>open override fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onAvailable](../-legacy-network-connection-api-impl/index.md#2110788460%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onAvailable](../-legacy-network-connection-api-impl/index.md#2110788460%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)) |
| [onBlockedStatusChanged](../../com.isupatches.android.wisefy.networkconnectionstatus.delegates/-legacy-network-connection-status-api-impl/index.md#1004516195%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onBlockedStatusChanged](../../com.isupatches.android.wisefy.networkconnectionstatus.delegates/-legacy-network-connection-status-api-impl/index.md#1004516195%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onCapabilitiesChanged](../-legacy-network-connection-api-impl/index.md#5611792%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onCapabilitiesChanged](../-legacy-network-connection-api-impl/index.md#5611792%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)) |
| [onLinkPropertiesChanged](../-legacy-network-connection-api-impl/index.md#973932568%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onLinkPropertiesChanged](../-legacy-network-connection-api-impl/index.md#973932568%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)) |
| [onLosing](../-legacy-network-connection-api-impl/index.md#-1693799552%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onLosing](../-legacy-network-connection-api-impl/index.md#-1693799552%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onLost](../-legacy-network-connection-api-impl/index.md#1243548751%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onLost](../-legacy-network-connection-api-impl/index.md#1243548751%2FFunctions%2F1622544596)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)) |
| [onUnavailable](../-legacy-network-connection-api-impl/index.md#-381201103%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [onUnavailable](../-legacy-network-connection-api-impl/index.md#-381201103%2FFunctions%2F1622544596)() |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [connectionManager](connection-manager.md) | [androidJvm]<br>private val [connectionManager](connection-manager.md): [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html) |
| [logger](logger.md) | [androidJvm]<br>private val [logger](logger.md): [WisefyLogger](../../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)? |
| [networkCallback](network-callback.md) | [androidJvm]<br>private val [networkCallback](network-callback.md): Android29NetworkConnectionApiImpl.<no name provided> |
