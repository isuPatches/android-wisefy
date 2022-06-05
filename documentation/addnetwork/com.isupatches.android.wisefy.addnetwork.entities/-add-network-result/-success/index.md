//[addnetwork](../../../../index.md)/[com.isupatches.android.wisefy.addnetwork.entities](../../index.md)/[AddNetworkResult](../index.md)/[Success](index.md)

# Success

[androidJvm]\
sealed class [Success](index.md) : [AddNetworkResult](../index.md)

A set of classes that are used to denote a success while attempting to add a network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.addnetwork.entities.AddNetworkResult](../index.md) |  |

## Types

| Name | Summary |
|---|---|
| [IntentLaunched](-intent-launched/index.md) | [androidJvm]<br>object [IntentLaunched](-intent-launched/index.md) : [AddNetworkResult.Success](index.md)<br>A data representation of a success while launching a network suggestion intent in Android 30. |
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [AddNetworkResult.Success](index.md)<br>A data representation of a success while attempting to add a network based on Android OS level returns. |

## Functions

| Name | Summary |
|---|---|
| [equals](../../-add-w-p-a3-network-request/-android30-or-above/index.md#585090901%2FFunctions%2F-271260435) | [androidJvm]<br>open operator fun [equals](../../-add-w-p-a3-network-request/-android30-or-above/index.md#585090901%2FFunctions%2F-271260435)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../-add-w-p-a3-network-request/-android30-or-above/index.md#1794629105%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [hashCode](../../-add-w-p-a3-network-request/-android30-or-above/index.md#1794629105%2FFunctions%2F-271260435)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../-add-w-p-a3-network-request/-android30-or-above/index.md#1616463040%2FFunctions%2F-271260435) | [androidJvm]<br>open fun [toString](../../-add-w-p-a3-network-request/-android30-or-above/index.md#1616463040%2FFunctions%2F-271260435)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [ResultCode](-result-code/index.md) |
| [IntentLaunched](-intent-launched/index.md) |
