//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[GetNetworkInfoRequest](index.md)

# GetNetworkInfoRequest

[androidJvm]\
data class [GetNetworkInfoRequest](index.md)(val network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null)

A data representation of a request to get information for a network.

*NOTES*

- 
   Assumes current network if a network is not passed in

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [GetNetworkInfoRequest](-get-network-info-request.md) | [androidJvm]<br>fun [GetNetworkInfoRequest](-get-network-info-request.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null): [GetNetworkInfoRequest](index.md) |
| [equals](../-network-info-data/index.md#585090901%2FFunctions%2F373461554) | [androidJvm]<br>open operator override fun [equals](../-network-info-data/index.md#585090901%2FFunctions%2F373461554)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-network-info-data/index.md#1794629105%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [hashCode](../-network-info-data/index.md#1794629105%2FFunctions%2F373461554)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-network-info-data/index.md#1616463040%2FFunctions%2F373461554) | [androidJvm]<br>open override fun [toString](../-network-info-data/index.md#1616463040%2FFunctions%2F373461554)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [network](network.md) | [androidJvm]<br>val [network](network.md): [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null<br>The optional network to use while getting network information |
