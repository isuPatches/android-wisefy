//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.delegates](../index.md)/[Android30AddNetworkDelegate](index.md)

# Android30AddNetworkDelegate

[androidJvm]\
@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)

internal class [Android30AddNetworkDelegate](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **impl**: [Android30AddNetworkApi](../-android30-add-network-api/index.md)) : [AddNetworkApi](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>open override fun [addOpenNetwork](add-open-network.md)(data: [OpenNetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-open-network-data/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [addWPA2Network](add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>open override fun [addWPA2Network](add-w-p-a2-network.md)(data: [WPA2NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a2-network-data/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [addWPA3Network](add-w-p-a3-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>open override fun [addWPA3Network](add-w-p-a3-network.md)(data: [WPA3NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a3-network-data/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [impl](impl.md) | [androidJvm]<br>private val [impl](impl.md): [Android30AddNetworkApi](../-android30-add-network-api/index.md) |
