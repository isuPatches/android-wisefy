//[wifi](../../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../../index.md)/[EnableWifiResult](../index.md)/[Failure](index.md)

# Failure

[androidJvm]\
sealed class [Failure](index.md) : [EnableWifiResult](../index.md)

A set of classes and objects that are data representations of a failure when enabling Wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.wifi.entities.EnableWifiResult](../index.md) |  |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [EnableWifiResult.Failure](index.md)<br>A representation of a failure enabling wifi due to hitting an unexpected path causing an assertion. |
| [UnableToEnable](-unable-to-enable/index.md) | [androidJvm]<br>object [UnableToEnable](-unable-to-enable/index.md) : [EnableWifiResult.Failure](index.md)<br>A representation of when there is a failure enabling wifi on pre-Android Q / SDK 29 devices. |

## Inheritors

| Name |
|---|
| [UnableToEnable](-unable-to-enable/index.md) |
| [Assertion](-assertion/index.md) |
