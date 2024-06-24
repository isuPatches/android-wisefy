//[core](../../index.md)/[com.isupatches.android.wisefy.core](index.md)/[supportsKeyManagementAlgorithm](supports-key-management-algorithm.md)

# supportsKeyManagementAlgorithm

[androidJvm]\
fun [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html).[supportsKeyManagementAlgorithm](supports-key-management-algorithm.md)(keyManagementAlgorithm: [KeyManagementAlgorithm](../com.isupatches.android.wisefy.core.entities/-key-management-algorithm/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

An extension function to check if the given access point supports a certain [KeyManagementAlgorithm](../com.isupatches.android.wisefy.core.entities/-key-management-algorithm/index.md) based on its rawValue.

#### Receiver

[ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)

#### Return

Boolean - True if the access point supports the [KeyManagementAlgorithm](../com.isupatches.android.wisefy.core.entities/-key-management-algorithm/index.md), otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| keyManagementAlgorithm | The given [KeyManagementAlgorithm](../com.isupatches.android.wisefy.core.entities/-key-management-algorithm/index.md) to check the access point for |

#### See also

| |
|---|
| [KeyManagementAlgorithm](../com.isupatches.android.wisefy.core.entities/-key-management-algorithm/index.md) |
