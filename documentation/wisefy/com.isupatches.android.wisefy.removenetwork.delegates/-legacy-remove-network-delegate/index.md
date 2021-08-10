//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.removenetwork.delegates](../index.md)/[LegacyRemoveNetworkDelegate](index.md)

# LegacyRemoveNetworkDelegate

[androidJvm]\
internal class [LegacyRemoveNetworkDelegate](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **savedNetworkUtil**: [SavedNetworkUtil](../../com.isupatches.android.wisefy.savednetworks/-saved-network-util/index.md), **impl**: [LegacyRemoveNetworkApi](../-legacy-remove-network-api/index.md)) : [RemoveNetworkApi](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>open override fun [removeNetwork](remove-network.md)(ssidToRemove: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [impl](impl.md) | [androidJvm]<br>private val [impl](impl.md): [LegacyRemoveNetworkApi](../-legacy-remove-network-api/index.md) |
| [savedNetworkUtil](saved-network-util.md) | [androidJvm]<br>private val [savedNetworkUtil](saved-network-util.md): [SavedNetworkUtil](../../com.isupatches.android.wisefy.savednetworks/-saved-network-util/index.md) |
| [wifiManager](wifi-manager.md) | [androidJvm]<br>private val [wifiManager](wifi-manager.md): [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html) |
