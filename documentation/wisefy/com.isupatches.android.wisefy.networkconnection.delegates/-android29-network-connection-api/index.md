//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkconnection.delegates](../index.md)/[Android29NetworkConnectionApi](index.md)

# Android29NetworkConnectionApi

[androidJvm]\
internal interface [Android29NetworkConnectionApi](index.md)

## Functions

| Name | Summary |
|---|---|
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>abstract fun [connectToNetwork](connect-to-network.md)(ssidToConnectTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md) |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Android29NetworkConnectionApiImpl](../-android29-network-connection-api-impl/index.md) |