//[security](../../../index.md)/[com.isupatches.android.wisefy.security.entities](../index.md)/[IsNetworkSecureRequest](index.md)

# IsNetworkSecureRequest

[androidJvm]\
data class [IsNetworkSecureRequest](index.md)(val network: AccessPointData)

A data class that is used to represent requests to check if a network is secure.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.accesspoints.entities.AccessPointData |  |

## Constructors

| | |
|---|---|
| [IsNetworkSecureRequest](-is-network-secure-request.md) | [androidJvm]<br>fun [IsNetworkSecureRequest](-is-network-secure-request.md)(network: AccessPointData) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): AccessPointData |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(network: AccessPointData): [IsNetworkSecureRequest](index.md) |
| [equals](../-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730) | [androidJvm]<br>open operator override fun [equals](../-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730) | [androidJvm]<br>open override fun [hashCode](../-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730) | [androidJvm]<br>open override fun [toString](../-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [network](network.md) | [androidJvm]<br>val [network](network.md): AccessPointData<br>The network to check if it is secure |
