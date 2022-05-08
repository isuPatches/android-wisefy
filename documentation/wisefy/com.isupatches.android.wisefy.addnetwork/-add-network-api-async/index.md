//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.addnetwork](../index.md)/[AddNetworkApiAsync](index.md)

# AddNetworkApiAsync

[androidJvm]\
interface [AddNetworkApiAsync](index.md)

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addOpenNetwork](add-open-network.md)(data: [OpenNetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-open-network-data/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-add-network-callbacks/index.md)?) |
| [addWPA2Network](add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA2Network](add-w-p-a2-network.md)(data: [WPA2NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a2-network-data/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-add-network-callbacks/index.md)?) |
| [addWPA3Network](add-w-p-a3-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA3Network](add-w-p-a3-network.md)(data: [WPA3NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a3-network-data/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-add-network-callbacks/index.md)?) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyApi](../../com.isupatches.android.wisefy/-wisefy-api/index.md) |
| [AddNetworkUtil](../-add-network-util/index.md) |