//[core](../../index.md)/[com.isupatches.android.wisefy.core.coroutines](index.md)/[createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md)

# createBaseCoroutineExceptionHandler

[androidJvm]\
fun [createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md)(callbacks: [BaseWisefyCallbacks](../com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)?): CoroutineExceptionHandler

A function to create a standardized coroutines exception handler for async operations within the library. This helps return exceptions in a standardized way for all async callbacks.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.base.BaseWisefyCallbacks](../com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| callbacks | The callback interface that implements BaseWisefyCallbacks to return exceptions to |
