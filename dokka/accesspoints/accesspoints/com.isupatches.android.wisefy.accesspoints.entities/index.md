//[accesspoints](../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AccessPointData](-access-point-data/index.md) | [androidJvm]<br>data class [AccessPointData](-access-point-data/index.md)(val rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), val ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, val rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, val is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5GHZ + 1 until MAX_FREQUENCY_5GHZ, val isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = SecurityCapability.ALL.any { rawValue.capabilities.contains(it.stringValue) })<br>A data representation of an Access Point. |
| [GetAccessPointsQuery](-get-access-points-query/index.md) | [androidJvm]<br>sealed class [GetAccessPointsQuery](-get-access-points-query/index.md)<br>A set of classes or objects that represent a query for access points. |
| [GetAccessPointsResult](-get-access-points-result/index.md) | [androidJvm]<br>sealed class [GetAccessPointsResult](-get-access-points-result/index.md)<br>A set of classes and objects that represent a result while querying for access points. |
| [SecurityCapability](-security-capability/index.md) | [androidJvm]<br>enum [SecurityCapability](-security-capability/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SecurityCapability](-security-capability/index.md)&gt; <br>A set of supported security capabilities. |

## Functions

| Name | Summary |
|---|---|
| [containSecurityCapability](contain-security-capability.md) | [androidJvm]<br>fun [AccessPointData](-access-point-data/index.md).[containSecurityCapability](contain-security-capability.md)(securityCapability: [SecurityCapability](-security-capability/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>An extension function to check if the given access point has a certain [SecurityCapability](-security-capability/index.md) based on its rawValue. |
