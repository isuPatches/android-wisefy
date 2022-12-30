//[core](../../../index.md)/[com.isupatches.android.wisefy.core.exceptions](../index.md)/[WisefyException](index.md)

# WisefyException

[androidJvm]\
class [WisefyException](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?) : [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)

A Wisefy specific throwable to help clients narrow their catch clauses down to a specific type if needed. This also standardizes how Wisefy returns errors from the async operations.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| message | The optional message for the exception |
| throwable | The optional cause of the exception |

## Constructors

| | |
|---|---|
| [WisefyException](-wisefy-exception.md) | [androidJvm]<br>fun [WisefyException](-wisefy-exception.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), throwable: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?) |

## Functions

| Name | Summary |
|---|---|
| [addSuppressed](index.md#282858770%2FFunctions%2F1616678122) | [androidJvm]<br>fun [addSuppressed](index.md#282858770%2FFunctions%2F1616678122)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [fillInStackTrace](index.md#-1102069925%2FFunctions%2F1616678122) | [androidJvm]<br>open fun [fillInStackTrace](index.md#-1102069925%2FFunctions%2F1616678122)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [getLocalizedMessage](index.md#1043865560%2FFunctions%2F1616678122) | [androidJvm]<br>open fun [getLocalizedMessage](index.md#1043865560%2FFunctions%2F1616678122)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getStackTrace](index.md#2050903719%2FFunctions%2F1616678122) | [androidJvm]<br>open fun [getStackTrace](index.md#2050903719%2FFunctions%2F1616678122)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt; |
| [getSuppressed](index.md#672492560%2FFunctions%2F1616678122) | [androidJvm]<br>fun [getSuppressed](index.md#672492560%2FFunctions%2F1616678122)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)&gt; |
| [initCause](index.md#-418225042%2FFunctions%2F1616678122) | [androidJvm]<br>open fun [initCause](index.md#-418225042%2FFunctions%2F1616678122)(p0: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html) |
| [printStackTrace](index.md#-1769529168%2FFunctions%2F1616678122) | [androidJvm]<br>open fun [printStackTrace](index.md#-1769529168%2FFunctions%2F1616678122)()<br>open fun [printStackTrace](index.md#1841853697%2FFunctions%2F1616678122)(p0: [PrintStream](https://developer.android.com/reference/kotlin/java/io/PrintStream.html))<br>open fun [printStackTrace](index.md#1175535278%2FFunctions%2F1616678122)(p0: [PrintWriter](https://developer.android.com/reference/kotlin/java/io/PrintWriter.html)) |
| [setStackTrace](index.md#2135801318%2FFunctions%2F1616678122) | [androidJvm]<br>open fun [setStackTrace](index.md#2135801318%2FFunctions%2F1616678122)(p0: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[StackTraceElement](https://developer.android.com/reference/kotlin/java/lang/StackTraceElement.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [cause](index.md#-654012527%2FProperties%2F1616678122) | [androidJvm]<br>open val [cause](index.md#-654012527%2FProperties%2F1616678122): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [message](index.md#1824300659%2FProperties%2F1616678122) | [androidJvm]<br>open val [message](index.md#1824300659%2FProperties%2F1616678122): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
