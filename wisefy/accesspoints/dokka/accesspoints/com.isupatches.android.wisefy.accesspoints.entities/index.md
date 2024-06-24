//[accesspoints](../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AccessPointData](-access-point-data/index.md) | [androidJvm]<br>data class [AccessPointData](-access-point-data/index.md)(val rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.ssidWithoutQuotes, val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.bssidWithoutQuotes, val frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, val rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, val is2gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_2_4_GHZ until MAX_FREQUENCY_2_4_GHZ, val is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5_GHZ until MAX_FREQUENCY_5_GHZ, val isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = AuthenticationAlgorithm.ALL.any { rawValue.supportsAuthenticationAlgorithm(it) })<br>A data representation of an Access Point. |
| [GetAccessPointsQuery](-get-access-points-query/index.md) | [androidJvm]<br>sealed class [GetAccessPointsQuery](-get-access-points-query/index.md)<br>A set of classes or objects that represent a query for access points. |
| [GetAccessPointsResult](-get-access-points-result/index.md) | [androidJvm]<br>sealed class [GetAccessPointsResult](-get-access-points-result/index.md)<br>A set of classes and objects that represent a result while querying for access points. |

## Functions

| Name | Summary |
|---|---|
| [supportsAuthenticationAlgorithm](supports-authentication-algorithm.md) | [androidJvm]<br>fun [AccessPointData](-access-point-data/index.md).[supportsAuthenticationAlgorithm](supports-authentication-algorithm.md)(authenticationAlgorithm: AuthenticationAlgorithm): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point supports a certain AuthenticationAlgorithm based on its rawValue. |
| [supportsKeyManagementAlgorithm](supports-key-management-algorithm.md) | [androidJvm]<br>fun [AccessPointData](-access-point-data/index.md).[supportsKeyManagementAlgorithm](supports-key-management-algorithm.md)(keyManagementAlgorithm: KeyManagementAlgorithm): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point supports a certain KeyManagementAlgorithm based on its rawValue. |
| [supportsPairwiseCipher](supports-pairwise-cipher.md) | [androidJvm]<br>fun [AccessPointData](-access-point-data/index.md).[supportsPairwiseCipher](supports-pairwise-cipher.md)(pairwiseCipher: PairwiseCipher): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point supports a certain PairwiseCipher based on its rawValue. |
