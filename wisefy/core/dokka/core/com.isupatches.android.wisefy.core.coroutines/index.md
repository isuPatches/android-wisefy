//[core](../../index.md)/[com.isupatches.android.wisefy.core.coroutines](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [CoroutineDispatcherProvider](-coroutine-dispatcher-provider/index.md) | [androidJvm]<br>interface [CoroutineDispatcherProvider](-coroutine-dispatcher-provider/index.md)<br>An API for references to various coroutines dispatchers (primarily used to swap them out in tests if needed). |
| [DefaultCoroutineDispatcherProvider](-default-coroutine-dispatcher-provider/index.md) | [androidJvm]<br>class [DefaultCoroutineDispatcherProvider](-default-coroutine-dispatcher-provider/index.md) : [CoroutineDispatcherProvider](-coroutine-dispatcher-provider/index.md)<br>An Default implementation for various coroutines dispatchers. |

## Functions

| Name | Summary |
|---|---|
| [createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md) | [androidJvm]<br>fun [createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md)(callbacks: [BaseWisefyCallbacks](../com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)?): CoroutineExceptionHandler<br>A function to create a standardized coroutines exception handler for async operations within the library. This helps return exceptions in a standardized way for all async callbacks. |
