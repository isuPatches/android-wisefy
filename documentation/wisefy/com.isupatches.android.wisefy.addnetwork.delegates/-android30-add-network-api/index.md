//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.delegates](../index.md)/[Android30AddNetworkApi](index.md)

# Android30AddNetworkApi

[androidJvm]\
internal interface [Android30AddNetworkApi](index.md)

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addOpenNetwork](add-open-network.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), activityResultLauncher: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)<[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)>): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [addWPA2Network](add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA2Network](add-w-p-a2-network.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), activityResultLauncher: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)<[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)>): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [addWPA3Network](add-w-p-a3-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA3Network](add-w-p-a3-network.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), passphrase: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), activityResultLauncher: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)<[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)>): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Android30AddNetworkApiImpl](../-android30-add-network-api-impl/index.md) |
