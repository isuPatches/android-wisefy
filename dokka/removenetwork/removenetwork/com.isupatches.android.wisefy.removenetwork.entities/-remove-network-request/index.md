//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../index.md)/[RemoveNetworkRequest](index.md)

# RemoveNetworkRequest

[androidJvm]\
sealed class [RemoveNetworkRequest](index.md)

A set of classes and objects that are used to represent requests for removing a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Types

| Name | Summary |
|---|---|
| [BSSID](-b-s-s-i-d/index.md) | [androidJvm]<br>data class [BSSID](-b-s-s-i-d/index.md)(val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [RemoveNetworkRequest](index.md)<br>A data representation to remove a network by BSSID. |
| [SSID](-s-s-i-d/index.md) | [androidJvm]<br>data class [SSID](-s-s-i-d/index.md)(val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [RemoveNetworkRequest](index.md)<br>A data representation to remove a network by SSID. |

## Inheritors

| Name |
|---|
| [SSID](-s-s-i-d/index.md) |
| [BSSID](-b-s-s-i-d/index.md) |
