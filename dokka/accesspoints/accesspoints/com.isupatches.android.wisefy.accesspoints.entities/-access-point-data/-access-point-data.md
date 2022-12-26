//[accesspoints](../../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](../index.md)/[AccessPointData](index.md)/[AccessPointData](-access-point-data.md)

# AccessPointData

[androidJvm]\
fun [AccessPointData](-access-point-data.md)(rawValue: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html), ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), bssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), frequency: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.frequency, rssi: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = rawValue.level, is5gHz: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = frequency in MIN_FREQUENCY_5GHZ + 1 until MAX_FREQUENCY_5GHZ, isSecure: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = SecurityCapability.ALL.any { rawValue.capabilities.contains(it.stringValue) })
