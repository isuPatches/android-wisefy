//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.frequency](../index.md)/[FrequencyUtil](index.md)

# FrequencyUtil

[androidJvm]\
internal interface [FrequencyUtil](index.md) : [FrequencyApi](../-frequency-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getFrequency](../-frequency-api/get-frequency.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [getFrequency](../-frequency-api/get-frequency.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [getFrequency](../-frequency-api/get-frequency.md)(network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [getFrequency](get-frequency.md)(callbacks: [GetFrequencyCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-frequency-callbacks/index.md)?) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isNetwork5gHz](../-frequency-api/is-network5g-hz.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [isNetwork5gHz](../-frequency-api/is-network5g-hz.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [isNetwork5gHz](../-frequency-api/is-network5g-hz.md)(network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [WisefyFrequencyUtil](../-wisefy-frequency-util/index.md) |