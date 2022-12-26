//[core](../../../index.md)/[com.isupatches.android.wisefy.core.assertions](../index.md)/[WisefyAssertions](index.md)

# WisefyAssertions

[androidJvm]\
class [WisefyAssertions](index.md)(throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

An assertion class that will allow Wisefy to assert for dev feedback to know about improper implementation or use.

*Note* This should be used in cases such as debug builds and for cases that are recoverable or less noticeable from the end user perspective. Typically this will be enabled by setting [throwOnAssertions](../../../../core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/throw-on-assertions.md) to be the same as something like BuildConfig.DEBUG.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Constructors

| | |
|---|---|
| [WisefyAssertions](-wisefy-assertions.md) | [androidJvm]<br>fun [WisefyAssertions](-wisefy-assertions.md)(throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [fail](fail.md) | [androidJvm]<br>fun [fail](fail.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>An assertion function that will only throw if assertions are enabled. |
