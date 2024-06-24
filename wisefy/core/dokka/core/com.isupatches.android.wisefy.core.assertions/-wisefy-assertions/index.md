//[core](../../../index.md)/[com.isupatches.android.wisefy.core.assertions](../index.md)/[WisefyAssertions](index.md)

# WisefyAssertions

class [WisefyAssertions](index.md)(throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

An assertion class that will allow Wisefy to assert for dev feedback to know about improper implementation or use.

*Note* This should be used in cases such as debug builds and for cases that are recoverable or less noticeable from the end user perspective. Typically this will be enabled by setting throwOnAssertions to be the same as something like BuildConfig.DEBUG.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| throwOnAssertions | If enabled, assertions will throw an IllegalArgumentException, otherwise they are no-op |

## Constructors

| | |
|---|---|
| [WisefyAssertions](-wisefy-assertions.md) | [androidJvm]<br>constructor(throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [fail](fail.md) | [androidJvm]<br>fun [fail](fail.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>An assertion function that will only throw if assertions are enabled. |
| [hashCode](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
