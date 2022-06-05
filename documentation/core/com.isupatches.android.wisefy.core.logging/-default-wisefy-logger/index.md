//[core](../../../index.md)/[com.isupatches.android.wisefy.core.logging](../index.md)/[DefaultWisefyLogger](index.md)

# DefaultWisefyLogger

[androidJvm]\
class [DefaultWisefyLogger](index.md) : [WisefyLogger](../-wisefy-logger/index.md)

A no-op, default implementation of [WisefyLogger](../-wisefy-logger/index.md).

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../-wisefy-logger/index.md) |  |

## Constructors

| | |
|---|---|
| [DefaultWisefyLogger](-default-wisefy-logger.md) | [androidJvm]<br>fun [DefaultWisefyLogger](-default-wisefy-logger.md)() |

## Functions

| Name | Summary |
|---|---|
| [d](d.md) | [androidJvm]<br>open override fun [d](d.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A function that logs a debug message. |
| [e](e.md) | [androidJvm]<br>open override fun [e](e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>open override fun [e](e.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A function that logs an error message. |
| [equals](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [i](i.md) | [androidJvm]<br>open override fun [i](i.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A function that logs an information message. |
| [toString](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [v](v.md) | [androidJvm]<br>open override fun [v](v.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A function that logs a verbose message. |
| [w](w.md) | [androidJvm]<br>open override fun [w](w.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A function that logs a warning message. |
| [wtf](wtf.md) | [androidJvm]<br>open override fun [wtf](wtf.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>open override fun [wtf](wtf.md)(tag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html), message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), vararg args: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A function that logs a &quot;what a terrible failure&quot; message. |
