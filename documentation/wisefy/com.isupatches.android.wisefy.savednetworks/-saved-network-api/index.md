//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[SavedNetworkApi](index.md)

# SavedNetworkApi

[androidJvm]\
interface [SavedNetworkApi](index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [getSavedNetworks](get-saved-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)> |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [isNetworkSaved](is-network-saved.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [searchForSavedNetwork](search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [searchForSavedNetwork](search-for-saved-network.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)? |
| [searchForSavedNetworks](search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [searchForSavedNetworks](search-for-saved-networks.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)> |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyApi](../../com.isupatches.android.wisefy/-wisefy-api/index.md) |
| [SavedNetworkUtil](../-saved-network-util/index.md) |
| [Android29SavedNetworkDelegate](../../com.isupatches.android.wisefy.savednetworks.delegates/-android29-saved-network-delegate/index.md) |
| [Android30SavedNetworkDelegate](../../com.isupatches.android.wisefy.savednetworks.delegates/-android30-saved-network-delegate/index.md) |
| [LegacySavedNetworkDelegate](../../com.isupatches.android.wisefy.savednetworks.delegates/-legacy-saved-network-delegate/index.md) |
