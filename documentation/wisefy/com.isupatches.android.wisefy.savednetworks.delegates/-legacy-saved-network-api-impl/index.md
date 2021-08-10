//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.delegates](../index.md)/[LegacySavedNetworkApiImpl](index.md)

# LegacySavedNetworkApiImpl

[androidJvm]\
internal class [LegacySavedNetworkApiImpl](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [LegacySavedNetworkApi](../-legacy-saved-network-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>open override fun [getSavedNetworks](get-saved-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)> |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>open override fun [isNetworkSaved](is-network-saved.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [matchesRegexForSSID](matches-regex-for-s-s-i-d.md) | [androidJvm]<br>private fun [matchesRegexForSSID](matches-regex-for-s-s-i-d.md)(configuration: [WifiConfiguration](https://developer.android.com/reference/kotlin/android/net/wifi/WifiConfiguration.html), regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [searchForSavedNetwork](search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>open override fun [searchForSavedNetwork](search-for-saved-network.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)? |
| [searchForSavedNetworks](search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>open override fun [searchForSavedNetworks](search-for-saved-networks.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)> |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [wifiManager](wifi-manager.md) | [androidJvm]<br>private val [wifiManager](wifi-manager.md): [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html) |
