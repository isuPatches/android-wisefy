//[core](../../../index.md)/[com.isupatches.android.wisefy.core.coroutines](../index.md)/[CoroutineDispatcherProvider](index.md)

# CoroutineDispatcherProvider

[androidJvm]\
class [CoroutineDispatcherProvider](index.md)

A class that contains references to various coroutines dispatchers (primarily used to swap them out in tests if needed).

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [CoroutineDispatcherProvider](-coroutine-dispatcher-provider.md) | [androidJvm]<br>fun [CoroutineDispatcherProvider](-coroutine-dispatcher-provider.md)() |

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#585090901%2FFunctions%2F1101426427)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1794629105%2FFunctions%2F1101426427)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.core.util/-sdk-util-impl/index.md#1616463040%2FFunctions%2F1101426427)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [io](io.md) | [androidJvm]<br>val [io](io.md): CoroutineDispatcher<br>A reference to the I/O dispatcher for background operations. |
| [main](main.md) | [androidJvm]<br>val [main](main.md): CoroutineDispatcher<br>A reference to the main dispatcher for UI operations. |
