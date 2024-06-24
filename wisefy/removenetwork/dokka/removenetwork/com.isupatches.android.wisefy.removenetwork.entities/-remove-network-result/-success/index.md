//[removenetwork](../../../../index.md)/[com.isupatches.android.wisefy.removenetwork.entities](../../index.md)/[RemoveNetworkResult](../index.md)/[Success](index.md)

# Success

sealed class [Success](index.md) : [RemoveNetworkResult](../index.md)

A set of classes that are used to denote a success while attempting to remove a network.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| [RemoveNetworkResult](../index.md) |

#### Inheritors

| |
|---|
| [ResultCode](-result-code/index.md) |
| [True](-true/index.md) |

## Types

| Name | Summary |
|---|---|
| [ResultCode](-result-code/index.md) | [androidJvm]<br>data class [ResultCode](-result-code/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [RemoveNetworkResult.Success](index.md)<br>A data representation of a success removing a network based on Android OS level returns. |
| [True](-true/index.md) | [androidJvm]<br>object [True](-true/index.md) : [RemoveNetworkResult.Success](index.md)<br>A data representation of a success removing a network on older Android OS levels. |

## Functions

| Name | Summary |
|---|---|
| [equals](../-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092) | [androidJvm]<br>open operator fun [equals](../-failure/-assertion/index.md#585090901%2FFunctions%2F-2039424092)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [hashCode](../-failure/-assertion/index.md#1794629105%2FFunctions%2F-2039424092)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092) | [androidJvm]<br>open fun [toString](../-failure/-assertion/index.md#1616463040%2FFunctions%2F-2039424092)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
