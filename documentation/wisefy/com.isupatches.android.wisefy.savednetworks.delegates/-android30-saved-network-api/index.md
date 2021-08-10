//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.savednetworks.delegates](../index.md)/[Android30SavedNetworkApi](index.md)

# Android30SavedNetworkApi

[androidJvm]\
internal interface [Android30SavedNetworkApi](index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_WIFI_STATE)<br>abstract fun [getSavedNetworks](get-saved-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)> |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_WIFI_STATE)<br>abstract fun [isNetworkSaved](is-network-saved.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [searchForSavedNetwork](search-for-saved-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_WIFI_STATE)<br>abstract fun [searchForSavedNetwork](search-for-saved-network.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)? |
| [searchForSavedNetworks](search-for-saved-networks.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_WIFI_STATE)<br>abstract fun [searchForSavedNetworks](search-for-saved-networks.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)> |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Android30SavedNetworkApiImpl](../-android30-saved-network-api-impl/index.md) |
