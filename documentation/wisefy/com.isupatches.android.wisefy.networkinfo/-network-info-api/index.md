//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoApi](index.md)

# NetworkInfoApi

[androidJvm]\
interface [NetworkInfoApi](index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>abstract fun [getCurrentNetwork](get-current-network.md)(): [CurrentNetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-data/index.md)? |
| [getCurrentNetworkInfo](get-current-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>abstract fun [getCurrentNetworkInfo](get-current-network-info.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null): [CurrentNetworkInfoData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-info-data/index.md)? |
| [getIP](get-i-p.md) | [androidJvm]<br>abstract fun [getIP](get-i-p.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyApi](../../com.isupatches.android.wisefy/-wisefy-api/index.md) |
| [NetworkInfoUtil](../-network-info-util/index.md) |
| [LegacyNetworkInfoDelegate](../../com.isupatches.android.wisefy.networkinfo.delegates/-legacy-network-info-delegate/index.md) |