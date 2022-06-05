//[networkinfo](../../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../../index.md)/[GetCurrentNetworkResult](../index.md)/[Network](index.md)

# Network

[androidJvm]\
data class [Network](index.md)(val data: [NetworkData](../../-network-data/index.md)) : [GetCurrentNetworkResult](../index.md)

A data representation for when there is a success retrieving the device's current network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.networkinfo.entities.GetCurrentNetworkResult](../index.md) |  |
| [com.isupatches.android.wisefy.networkinfo.entities.NetworkData](../../-network-data/index.md) |  |

## Constructors

| | |
|---|---|
| [Network](-network.md) | [androidJvm]<br>fun [Network](-network.md)(data: [NetworkData](../../-network-data/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [NetworkData](../../-network-data/index.md) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(data: [NetworkData](../../-network-data/index.md)): [GetCurrentNetworkResult.Network](index.md) |
| [equals](../../-network-info-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator override fun [equals](../../-network-info-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-network-info-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [hashCode](../../-network-info-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-network-info-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [toString](../../-network-info-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [data](data.md) | [androidJvm]<br>val [data](data.md): [NetworkData](../../-network-data/index.md)<br>The value of the device's current network |
