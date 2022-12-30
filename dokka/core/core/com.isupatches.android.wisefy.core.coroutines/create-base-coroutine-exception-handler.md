//[core](../../index.md)/[com.isupatches.android.wisefy.core.coroutines](index.md)/[createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md)

# createBaseCoroutineExceptionHandler

[androidJvm]\
fun [createBaseCoroutineExceptionHandler](create-base-coroutine-exception-handler.md)(callbacks: [BaseWisefyCallbacks](../com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md)?): CoroutineExceptionHandler

A function to create a standardized coroutines exception handler for async operations within the library. This helps return exceptions in a standardized way for all async callbacks.

*Notes* Converts a throwable to a [WisefyException](../com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) with the cause being the caught throwable

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [BaseWisefyCallbacks](../com.isupatches.android.wisefy.core.base/-base-wisefy-callbacks/index.md) |
| [WisefyException](../com.isupatches.android.wisefy.core.exceptions/-wisefy-exception/index.md) |

#### Parameters

androidJvm

| | |
|---|---|
| callbacks | The callback interface that implements BaseWisefyCallbacks to return exceptions to |
