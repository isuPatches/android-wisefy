//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkconnection](../index.md)/[WisefyNetworkConnectionUtil](index.md)

# WisefyNetworkConnectionUtil

[androidJvm]\
internal class [WisefyNetworkConnectionUtil](index.md)(**coroutineDispatcherProvider**: [CoroutineDispatcherProvider](../../com.isupatches.android.wisefy.util.coroutines/-coroutine-dispatcher-provider/index.md), **connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)?, **networkConnectionStatusUtil**: [NetworkConnectionStatusUtil](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-util/index.md), **savedNetworkUtil**: [SavedNetworkUtil](../../com.isupatches.android.wisefy.savednetworks/-saved-network-util/index.md), **sdkUtil**: [SdkUtil](../../com.isupatches.android.wisefy.util/-sdk-util/index.md), **wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [NetworkConnectionUtil](../-network-connection-util/index.md)

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>open override fun [connectToNetwork](connect-to-network.md)(ssidToConnectTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>open override fun [connectToNetwork](connect-to-network.md)(ssidToConnectTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-connect-to-network-callbacks/index.md)?) |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>open override fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>open override fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-disconnect-from-current-network-callbacks/index.md)?) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [coroutineDispatcherProvider](coroutine-dispatcher-provider.md) | [androidJvm]<br>private val [coroutineDispatcherProvider](coroutine-dispatcher-provider.md): [CoroutineDispatcherProvider](../../com.isupatches.android.wisefy.util.coroutines/-coroutine-dispatcher-provider/index.md) |
| [delegate](delegate.md) | [androidJvm]<br>private val [delegate](delegate.md): [NetworkConnectionApi](../-network-connection-api/index.md) |
| [networkConnectionScope](network-connection-scope.md) | [androidJvm]<br>private val [networkConnectionScope](network-connection-scope.md): CoroutineScope |
