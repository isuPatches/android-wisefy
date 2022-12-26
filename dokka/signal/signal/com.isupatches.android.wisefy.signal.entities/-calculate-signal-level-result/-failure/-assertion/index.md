//[signal](../../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../../index.md)/[CalculateSignalLevelResult](../../index.md)/[Failure](../index.md)/[Assertion](index.md)

# Assertion

[androidJvm]\
data class [Assertion](index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [CalculateSignalLevelResult.Failure](../index.md)

A representation of a failure to calculate the sign level of a network due to hitting an unexpected path causing an assertion.

*NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.signal.entities.CalculateSignalLevelResult.Failure](../index.md) |  |

## Constructors

| | |
|---|---|
| [Assertion](-assertion.md) | [androidJvm]<br>fun [Assertion](-assertion.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [message](message.md) | [androidJvm]<br>val [message](message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A text description describing the assertion error hit |
