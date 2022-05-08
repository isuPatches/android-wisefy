//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.networkinfo](../index.md)/[NetworkInfoUtil](index.md)

# NetworkInfoUtil

[androidJvm]\
internal interface [NetworkInfoUtil](index.md) : [NetworkInfoApi](../-network-info-api/index.md), [NetworkInfoApiAsync](../-network-info-api-async/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](../-network-info-api/get-current-network.md) | [androidJvm]<br>abstract fun [getCurrentNetwork](../-network-info-api/get-current-network.md)(): [CurrentNetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-data/index.md)?<br>abstract fun [getCurrentNetwork](../-network-info-api-async/get-current-network.md)(callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-current-network-callbacks/index.md)?) |
| [getCurrentNetworkInfo](../-network-info-api/get-current-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>abstract fun [getCurrentNetworkInfo](../-network-info-api/get-current-network-info.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null): [CurrentNetworkInfoData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-info-data/index.md)?<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>abstract fun [getCurrentNetworkInfo](../-network-info-api-async/get-current-network-info.md)(callbacks: [GetCurrentNetworkInfoCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-current-network-info-callbacks/index.md)?, network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null) |
| [getIP](../-network-info-api/get-i-p.md) | [androidJvm]<br>abstract fun [getIP](../-network-info-api/get-i-p.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>abstract fun [getIP](../-network-info-api-async/get-i-p.md)(callbacks: [GetIPCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-i-p-callbacks/index.md)?) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyNetworkInfoUtil](../-wisefy-network-info-util/index.md) |