//[accesspoints](../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](index.md)/[supportsKeyManagementAlgorithm](supports-key-management-algorithm.md)

# supportsKeyManagementAlgorithm

[androidJvm]\
fun [AccessPointData](-access-point-data/index.md).[supportsKeyManagementAlgorithm](supports-key-management-algorithm.md)(keyManagementAlgorithm: KeyManagementAlgorithm): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

An extension function to check if the given access point supports a certain KeyManagementAlgorithm based on its rawValue.

#### Receiver

[AccessPointData](-access-point-data/index.md)

#### Return

Boolean - True if the access point supports the KeyManagementAlgorithm, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| keyManagementAlgorithm | The given KeyManagementAlgorithm to check the access point for |

#### See also

| |
|---|
| KeyManagementAlgorithm |
