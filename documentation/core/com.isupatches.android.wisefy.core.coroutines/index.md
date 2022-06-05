//[core](../../index.md)/[com.isupatches.android.wisefy.core.coroutines](index.md)

# Package com.isupatches.android.wisefy.core.coroutines

## Types

| Name | Summary |
|---|---|
| [CoroutineDispatcherProvider](-coroutine-dispatcher-provider/index.md) | [androidJvm]<br>class [CoroutineDispatcherProvider](-coroutine-dispatcher-provider/index.md)<br>A class that contains references to various coroutines dispatchers (primarily used to swap them out in tests if needed). |

## Functions

| Name | Summary |
|---|---|
| [createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md) | [androidJvm]<br>fun [createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md)(callbacks: [BaseWisefyCallbacks](../com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)?): CoroutineExceptionHandler<br>A function to create a standardized coroutines exception handler for async operations within the library. This helps return exceptions in a standardized way for all async callbacks. |
