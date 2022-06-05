//[frequency](../../../index.md)/[com.isupatches.android.wisefy.frequency.entities](../index.md)/[IsNetwork5gHzRequest](index.md)

# IsNetwork5gHzRequest

[androidJvm]\
sealed class [IsNetwork5gHzRequest](index.md)

A set of classes and objects that are used to represent requests to check if a network is 5G.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [CurrentNetwork](-current-network/index.md) | [androidJvm]<br>object [CurrentNetwork](-current-network/index.md) : [IsNetwork5gHzRequest](index.md)<br>A data representation of a request to check if the current network is 5G. |
| [ForNetwork](-for-network/index.md) | [androidJvm]<br>data class [ForNetwork](-for-network/index.md)(val network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)) : [IsNetwork5gHzRequest](index.md)<br>A data representation of a request to check if a given current network is 5G. |

## Inheritors

| Name |
|---|
| [CurrentNetwork](-current-network/index.md) |
| [ForNetwork](-for-network/index.md) |
