//[wifi](../../../../index.md)/[com.isupatches.android.wisefy.wifi.entities](../../index.md)/[DisableWifiResult](../index.md)/[Failure](index.md)

# Failure

sealed class [Failure](index.md) : [DisableWifiResult](../index.md)

A set of classes and objects that are data representations of a failure when disabling Wifi.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [DisableWifiResult](../index.md) |

#### Inheritors

| |
|---|
| [UnableToDisable](-unable-to-disable/index.md) |
| [Assertion](-assertion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Assertion](-assertion/index.md) | [androidJvm]<br>data class [Assertion](-assertion/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [DisableWifiResult.Failure](index.md)<br>A representation of a failure disabling wifi due to hitting an unexpected path causing an assertion. |
| [UnableToDisable](-unable-to-disable/index.md) | [androidJvm]<br>object [UnableToDisable](-unable-to-disable/index.md) : [DisableWifiResult.Failure](index.md)<br>A representation of when there is a failure disabling wifi on pre-Android Q / SDK 29 devices. |

## Functions

| Name | Summary |
|---|---|
| [equals](../../-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363) | [androidJvm]<br>open operator fun [equals](../../-is-wifi-enabled-result/-false/index.md#585090901%2FFunctions%2F-130402363)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [hashCode](../../-is-wifi-enabled-result/-false/index.md#1794629105%2FFunctions%2F-130402363)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363) | [androidJvm]<br>open fun [toString](../../-is-wifi-enabled-result/-false/index.md#1616463040%2FFunctions%2F-130402363)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
