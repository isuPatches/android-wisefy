//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../index.md)/[OpenNetworkData](index.md)

# OpenNetworkData

[androidJvm]\
sealed class [OpenNetworkData](index.md)

## Types

| Name | Summary |
|---|---|
| [Ssid](-ssid/index.md) | [androidJvm]<br>data class [Ssid](-ssid/index.md)(**ssid**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [OpenNetworkData](index.md) |
| [SsidAndActivityResultLauncher](-ssid-and-activity-result-launcher/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>data class [SsidAndActivityResultLauncher](-ssid-and-activity-result-launcher/index.md)(**ssid**: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), **activityResultLauncher**: [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)<[Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)>) : [OpenNetworkData](index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [OpenNetworkData](-ssid/index.md) |
| [OpenNetworkData](-ssid-and-activity-result-launcher/index.md) |