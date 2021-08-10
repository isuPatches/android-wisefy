//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.signal.delegates](../index.md)/[Android30SignalDelegate](index.md)

# Android30SignalDelegate

[androidJvm]\
internal class [Android30SignalDelegate](index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **impl**: [Android30SignalApi](../-android30-signal-api/index.md)) : [SignalApi](../../com.isupatches.android.wisefy.signal/-signal-api/index.md)

## Functions

| Name | Summary |
|---|---|
| [calculateBars](calculate-bars.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>open override fun [calculateBars](calculate-bars.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>~~open~~ ~~override~~ ~~fun~~ [~~calculateBars~~](calculate-bars.md)~~(~~~~rssiLevel~~~~:~~ [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~,~~ ~~targetNumberOfBars~~~~:~~ [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~)~~~~:~~ [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>open override fun [compareSignalLevel](compare-signal-level.md)(rssi1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), rssi2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [impl](impl.md) | [androidJvm]<br>private val [impl](impl.md): [Android30SignalApi](../-android30-signal-api/index.md) |
