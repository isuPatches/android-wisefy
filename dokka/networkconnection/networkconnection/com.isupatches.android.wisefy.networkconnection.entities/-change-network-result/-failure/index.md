//[networkconnection](../../../../index.md)/[com.isupatches.android.wisefy.networkconnection.entities](../../index.md)/[ChangeNetworkResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [ChangeNetworkResult](../index.md)

A set of classes and objects that are representations of a failure while changing the device's current network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [ChangeNetworkResult](../index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ChangeNetworkResult.Failure](index.md)<br>A representation of a failure changing the device's current network due to hitting an unexpected path causing an assertion. |

## Inheritors

| Name |
|---|
| [Assertion](-assertion/index.md) |
