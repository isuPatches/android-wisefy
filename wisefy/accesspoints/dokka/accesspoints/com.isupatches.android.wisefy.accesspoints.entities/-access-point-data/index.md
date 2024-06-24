//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[AccessPointData](index.md)

# AccessPointData

data class [AccessPointData](index.md)(val rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.ssidWithoutQuotes, val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.bssidWithoutQuotes, val frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, val rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, val is2gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_2_4_GHZ until MAX_FREQUENCY_2_4_GHZ, val is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5_GHZ until MAX_FREQUENCY_5_GHZ, val isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = AuthenticationAlgorithm.ALL.any { rawValue.supportsAuthenticationAlgorithm(it) })

A data representation of an Access Point.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| AuthenticationAlgorithm |
| supportsAuthenticationAlgorithm |
| bssidWithoutQuotes |
| ssidWithoutQuotes |

## Constructors

| | |
|---|---|
| [AccessPointData](-access-point-data.md) | [androidJvm]<br>constructor(rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.ssidWithoutQuotes, bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.bssidWithoutQuotes, frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, is2gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_2_4_GHZ until MAX_FREQUENCY_2_4_GHZ, is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5_GHZ until MAX_FREQUENCY_5_GHZ, isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = AuthenticationAlgorithm.ALL.any { rawValue.supportsAuthenticationAlgorithm(it) }) |

## Properties

| Name | Summary |
|---|---|
| [bssid](bssid.md) | [androidJvm]<br>val [bssid](bssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A convenience property to expose the BSSID of the access point from the [rawValue](raw-value.md) |
| [frequency](frequency.md) | [androidJvm]<br>val [frequency](frequency.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A convenience property to expose the frequency of the access point from the [rawValue](raw-value.md) |
| [is2gHz](is2g-hz.md) | [androidJvm]<br>val [is2gHz](is2g-hz.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience property to check if the access point is a 2.4gHz network based on its [rawValue](raw-value.md) |
| [is5gHz](is5g-hz.md) | [androidJvm]<br>val [is5gHz](is5g-hz.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience property to check if the access point is a 5gHz network based on its [rawValue](raw-value.md) |
| [isSecure](is-secure.md) | [androidJvm]<br>val [isSecure](is-secure.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience property to check if the access point has any of the AuthenticationAlgorithm values listed based on its [rawValue](raw-value.md) |
| [rawValue](raw-value.md) | [androidJvm]<br>val [rawValue](raw-value.md): [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)<br>The direct Android OS information about the access point |
| [rssi](rssi.md) | [androidJvm]<br>val [rssi](rssi.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A convenience property to expose the RSSI level of the access point from the [rawValue](raw-value.md) |
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A convenience property to expose the SSID of the access point from the [rawValue](raw-value.md) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component4](component4.md) | [androidJvm]<br>operator fun [component4](component4.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [component5](component5.md) | [androidJvm]<br>operator fun [component5](component5.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [component6](component6.md) | [androidJvm]<br>operator fun [component6](component6.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [component7](component7.md) | [androidJvm]<br>operator fun [component7](component7.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [component8](component8.md) | [androidJvm]<br>operator fun [component8](component8.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.ssidWithoutQuotes, bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = rawValue.bssidWithoutQuotes, frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, is2gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_2_4_GHZ until MAX_FREQUENCY_2_4_GHZ, is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5_GHZ until MAX_FREQUENCY_5_GHZ, isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = AuthenticationAlgorithm.ALL.any { rawValue.supportsAuthenticationAlgorithm(it) }): [AccessPointData](index.md) |
| [equals](../-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819) | [androidJvm]<br>open operator override fun [equals](../-get-access-points-result/-access-points/index.md#585090901%2FFunctions%2F974708819)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [hashCode](../-get-access-points-result/-access-points/index.md#1794629105%2FFunctions%2F974708819)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [supportsAuthenticationAlgorithm](../supports-authentication-algorithm.md) | [androidJvm]<br>fun [AccessPointData](index.md).[supportsAuthenticationAlgorithm](../supports-authentication-algorithm.md)(authenticationAlgorithm: AuthenticationAlgorithm): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point supports a certain AuthenticationAlgorithm based on its rawValue. |
| [supportsKeyManagementAlgorithm](../supports-key-management-algorithm.md) | [androidJvm]<br>fun [AccessPointData](index.md).[supportsKeyManagementAlgorithm](../supports-key-management-algorithm.md)(keyManagementAlgorithm: KeyManagementAlgorithm): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point supports a certain KeyManagementAlgorithm based on its rawValue. |
| [supportsPairwiseCipher](../supports-pairwise-cipher.md) | [androidJvm]<br>fun [AccessPointData](index.md).[supportsPairwiseCipher](../supports-pairwise-cipher.md)(pairwiseCipher: PairwiseCipher): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point supports a certain PairwiseCipher based on its rawValue. |
| [toString](../-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819) | [androidJvm]<br>open override fun [toString](../-get-access-points-result/-access-points/index.md#1616463040%2FFunctions%2F974708819)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
