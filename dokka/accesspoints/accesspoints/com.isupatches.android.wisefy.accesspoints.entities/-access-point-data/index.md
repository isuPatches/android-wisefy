//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[AccessPointData](index.md)

# AccessPointData

[androidJvm]\
data class [AccessPointData](index.md)(val rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, val rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, val is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5GHZ + 1 until MAX_FREQUENCY_5GHZ, val isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = SecurityCapability.ALL.any { rawValue.capabilities.contains(it.stringValue) })

A data representation of an Access Point.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

androidJvm

| |
|---|
| [SecurityCapability](../-security-capability/index.md) |

## Constructors

| | |
|---|---|
| [AccessPointData](-access-point-data.md) | [androidJvm]<br>fun [AccessPointData](-access-point-data.md)(rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5GHZ + 1 until MAX_FREQUENCY_5GHZ, isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = SecurityCapability.ALL.any { rawValue.capabilities.contains(it.stringValue) }) |

## Properties

| Name | Summary |
|---|---|
| [bssid](bssid.md) | [androidJvm]<br>val [bssid](bssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A convenience property to expose the BSSID of the access point from the [rawValue](raw-value.md) |
| [frequency](frequency.md) | [androidJvm]<br>val [frequency](frequency.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A convenience property to expose the frequency of the access point from the [rawValue](raw-value.md) |
| [is5gHz](is5g-hz.md) | [androidJvm]<br>val [is5gHz](is5g-hz.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience property to check if the access point is 5gHz based on its [rawValue](raw-value.md) |
| [isSecure](is-secure.md) | [androidJvm]<br>val [isSecure](is-secure.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A convenience property to check if the access point has any of the [SecurityCapability](../-security-capability/index.md) values listed based on its [rawValue](raw-value.md) |
| [rawValue](raw-value.md) | [androidJvm]<br>val [rawValue](raw-value.md): [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)<br>The direct Android OS information about the access point |
| [rssi](rssi.md) | [androidJvm]<br>val [rssi](rssi.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>A convenience property to expose the RSSI level of the access point from the [rawValue](raw-value.md) |
| [ssid](ssid.md) | [androidJvm]<br>val [ssid](ssid.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A convenience property to expose the SSID of the access point from the [rawValue](raw-value.md) |

## Extensions

| Name | Summary |
|---|---|
| [containSecurityCapability](../contain-security-capability.md) | [androidJvm]<br>fun [AccessPointData](index.md).[containSecurityCapability](../contain-security-capability.md)(securityCapability: [SecurityCapability](../-security-capability/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point has a certain [SecurityCapability](../-security-capability/index.md) based on its rawValue. |
