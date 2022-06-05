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

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
