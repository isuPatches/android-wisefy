//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.delegates](../index.md)/[LegacyNetworkInfoApiImpl](index.md)

# LegacyNetworkInfoApiImpl

[androidJvm]\
internal class [LegacyNetworkInfoApiImpl](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **connectivityManager**: [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)?) : [LegacyNetworkInfoApi](../-legacy-network-info-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>open override fun [getCurrentNetwork](get-current-network.md)(): [CurrentNetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-data/index.md)? |
| [getCurrentNetworkInfo](get-current-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>open override fun [getCurrentNetworkInfo](get-current-network-info.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)?): [CurrentNetworkInfoData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-info-data/index.md)? |
| [getIP](get-i-p.md) | [androidJvm]<br>open override fun [getIP](get-i-p.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [connectivityManager](connectivity-manager.md) | [androidJvm]<br>private val [connectivityManager](connectivity-manager.md): [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html) |
| [logger](logger.md) | [androidJvm]<br>private val [logger](logger.md): [WisefyLogger](../../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)? |
| [wifiManager](wifi-manager.md) | [androidJvm]<br>private val [wifiManager](wifi-manager.md): [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html) |
