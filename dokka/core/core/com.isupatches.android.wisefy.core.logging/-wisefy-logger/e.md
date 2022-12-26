//[core](../../../index.md)/[com.isupatches.android.wisefy.core.logging](../index.md)/[WisefyLogger](index.md)/[e](e.md)

# e

[androidJvm]\
abstract fun [e](e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

A function that logs an error message.

#### Return

Int - The amount of bytes logged

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| tag | The tag for the log message |
| message | The message to log (can include placeholders) |
| args | The formatting arguments for the log message |

[androidJvm]\
abstract fun [e](e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

A function that logs an error message.

#### Return

Int - The amount of bytes logged

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

## Parameters

androidJvm

| | |
|---|---|
| tag | The tag for the log message |
| throwable | An exception to include with the error log |
| message | The message to log (can include placeholders) |
| args | The formatting arguments for the log message |
