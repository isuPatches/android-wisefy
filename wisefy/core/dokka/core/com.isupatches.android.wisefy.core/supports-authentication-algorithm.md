//[core](../../index.md)/[com.isupatches.android.wisefy.core](index.md)/[supportsAuthenticationAlgorithm](supports-authentication-algorithm.md)

# supportsAuthenticationAlgorithm

[androidJvm]\
fun [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html).[supportsAuthenticationAlgorithm](supports-authentication-algorithm.md)(authenticationAlgorithm: [AuthenticationAlgorithm](../com.isupatches.android.wisefy.core.entities/-authentication-algorithm/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

An extension function to check if the given access point supports a certain [AuthenticationAlgorithm](../com.isupatches.android.wisefy.core.entities/-authentication-algorithm/index.md) based on its rawValue.

#### Receiver

[ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)

#### Return

Boolean - True if the access point supports the [AuthenticationAlgorithm](../com.isupatches.android.wisefy.core.entities/-authentication-algorithm/index.md), otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| authenticationAlgorithm | The given [AuthenticationAlgorithm](../com.isupatches.android.wisefy.core.entities/-authentication-algorithm/index.md) to check the access point for |

#### See also

| |
|---|
| [AuthenticationAlgorithm](../com.isupatches.android.wisefy.core.entities/-authentication-algorithm/index.md) |
