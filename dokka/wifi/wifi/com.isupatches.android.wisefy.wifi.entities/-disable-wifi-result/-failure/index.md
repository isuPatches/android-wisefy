//[wifi](../../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../../index.md)/[DisableWifiResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [DisableWifiResult](../index.md)

A set of classes and objects that are data representations of a failure when disabling Wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [DisableWifiResult](../index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [DisableWifiResult.Failure](index.md)<br>A representation of a failure disabling wifi due to hitting an unexpected path causing an assertion. |
| [UnableToDisable](-unable-to-disable/index.md) | [androidJvm]<br>object [UnableToDisable](-unable-to-disable/index.md) : [DisableWifiResult.Failure](index.md)<br>A representation of when there is a failure disabling wifi on pre-Android Q / SDK 29 devices. |

## Inheritors

| Name |
|---|
| [UnableToDisable](-unable-to-disable/index.md) |
| [Assertion](-assertion/index.md) |
