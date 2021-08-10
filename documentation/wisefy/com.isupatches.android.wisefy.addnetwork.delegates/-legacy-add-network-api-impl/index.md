//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.delegates](../index.md)/[LegacyAddNetworkApiImpl](index.md)

# LegacyAddNetworkApiImpl

[androidJvm]\
internal class [LegacyAddNetworkApiImpl](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [LegacyAddNetworkApi](../-legacy-add-network-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>open override fun [addOpenNetwork](add-open-network.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [addWPA2Network](add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>open override fun [addWPA2Network](add-w-p-a2-network.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [wifiManager](wifi-manager.md) | [androidJvm]<br>private val [wifiManager](wifi-manager.md): [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html) |
