//[networkconnectionstatus](../../../index.md)/[com.isupatches.android.wisefy.networkconnectionstatus.entities](../index.md)/[IsDeviceConnectedToSSIDRequest](index.md)

# IsDeviceConnectedToSSIDRequest

[androidJvm]\
sealed class [IsDeviceConnectedToSSIDRequest](index.md)

A set of classes and objects that are used to represent requests checking if the device is connected to a matching network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [IsDeviceConnectedToSSIDRequest](index.md)<br>A data representation to check if the device is connected to a network given an BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val regex: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [IsDeviceConnectedToSSIDRequest](index.md)<br>A data representation to check if the device is connected to a network given an SSID. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-is-device-roaming-result/-false/index.md#585090901%2FFunctions%2F1246821712) | [androidJvm]<br>open operator fun [equals](../-is-device-roaming-result/-false/index.md#585090901%2FFunctions%2F1246821712)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-is-device-roaming-result/-false/index.md#1794629105%2FFunctions%2F1246821712) | [androidJvm]<br>open fun [hashCode](../-is-device-roaming-result/-false/index.md#1794629105%2FFunctions%2F1246821712)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-is-device-roaming-result/-false/index.md#1616463040%2FFunctions%2F1246821712) | [androidJvm]<br>open fun [toString](../-is-device-roaming-result/-false/index.md#1616463040%2FFunctions%2F1246821712)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
