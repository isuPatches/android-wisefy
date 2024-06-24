//[addnetwork](../../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../../index.md)/[AddNetworkResult](../../index.md)/[Failure](../index.md)/[Assertion](index.md)

# Assertion

data class [Assertion](index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [AddNetworkResult.Failure](../index.md)

A representation of a failure to add a network due to hitting an unexpected path causing an assertion.

*NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [AddNetworkResult.Failure](../index.md) |

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
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [AddNetworkResult.Failure.Assertion](index.md) |
| [equals](index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator override fun [equals](index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [hashCode](index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open override fun [toString](index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
