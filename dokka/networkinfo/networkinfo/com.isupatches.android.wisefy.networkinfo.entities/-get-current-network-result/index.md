//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[GetCurrentNetworkResult](index.md)

# GetCurrentNetworkResult

[androidJvm]\
sealed class [GetCurrentNetworkResult](index.md)

A set of classes and objects that are used to represent results for getting the device's current network.

#### Author

Patches Klinefelter

#### Since

03/2022

## Types

| Name | Summary |
|---|---|
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [GetCurrentNetworkResult](index.md)<br>A data representation for when there is no current network for the device. |
| [Network](-network/index.md) | [androidJvm]<br>data class [Network](-network/index.md)(val data: [NetworkData](../-network-data/index.md)) : [GetCurrentNetworkResult](index.md)<br>A data representation for when there is a success retrieving the device's current network. |

## Inheritors

| Name |
|---|
| [Empty](-empty/index.md) |
| [Network](-network/index.md) |
