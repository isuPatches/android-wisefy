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

## Properties

| Name | Summary |
|---|---|
| [io](io.md) | [androidJvm]<br>val [io](io.md): CoroutineDispatcher<br>A reference to the I/O dispatcher for background operations. |
| [main](main.md) | [androidJvm]<br>val [main](main.md): CoroutineDispatcher<br>A reference to the main dispatcher for UI operations. |
