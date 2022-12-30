//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[DisconnectFromCurrentNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [DisconnectFromCurrentNetworkResult](../index.md)

A set of classes and objects that are representations of a failure when disconnecting from the current network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [DisconnectFromCurrentNetworkResult](../index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [DisconnectFromCurrentNetworkResult.Failure](index.md)<br>A representation of a failure disconnecting from the current network due to hitting an unexpected path causing an assertion. |
| [False](-false/index.md) | [androidJvm]<br>object [False](-false/index.md) : [DisconnectFromCurrentNetworkResult.Failure](index.md)<br>A data representation for when there is a failure disconnecting from the current network. |

## Inheritors

| Name |
|---|
| [False](-false/index.md) |
| [Assertion](-assertion/index.md) |
