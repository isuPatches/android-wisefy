//[signal](../../../../../index.md)/[com.isupatches.android.wisefy.signal.entities](../../../index.md)/[CalculateSignalLevelResult](../../index.md)/[Failure](../index.md)/[Assertion](index.md)

# Assertion

data class [Assertion](index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [CalculateSignalLevelResult.Failure](../index.md)

A representation of a failure to calculate the sign level of a network due to hitting an unexpected path causing an assertion.

*NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [CalculateSignalLevelResult.Failure](../index.md) |

## Constructors

| | |
|---|---|
| [Assertion](-assertion.md) | [androidJvm]<br>constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [message](message.md) | [androidJvm]<br>val [message](message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A text description describing the assertion error hit |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CalculateSignalLevelResult.Failure.Assertion](index.md) |
| [equals](../../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514) | [androidJvm]<br>open operator override fun [equals](../../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#585090901%2FFunctions%2F1816002514)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [hashCode](../../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1794629105%2FFunctions%2F1816002514)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514) | [androidJvm]<br>open override fun [toString](../../../-compare-signal-level-result/-success/-first-r-s-s-i-value-is-stronger/index.md#1616463040%2FFunctions%2F1816002514)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
