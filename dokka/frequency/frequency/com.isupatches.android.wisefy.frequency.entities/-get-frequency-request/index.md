//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency.entities](../index.md)/[GetFrequencyRequest](index.md)

# GetFrequencyRequest

[androidJvm]\
sealed class [GetFrequencyRequest](index.md)

A set of classes and objects that are used to represent requests to get a network's frequency.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [CurrentNetwork](-current-network/index.md) | [androidJvm]<br>object [CurrentNetwork](-current-network/index.md) : [GetFrequencyRequest](index.md)<br>A data representation of a request to get the current network's frequency. |
| [ForNetwork](-for-network/index.md) | [androidJvm]<br>data class [ForNetwork](-for-network/index.md)(val network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)) : [GetFrequencyRequest](index.md)<br>A data representation of a request to get a given network's frequency. |

## Inheritors

| Name |
|---|
| [CurrentNetwork](-current-network/index.md) |
| [ForNetwork](-for-network/index.md) |
