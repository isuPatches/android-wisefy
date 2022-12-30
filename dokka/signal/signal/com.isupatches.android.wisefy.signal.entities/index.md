//[signal](../../index.md)/[com.isupatches.android.wisefy.signal.entities](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [CalculateSignalLevelRequest](-calculate-signal-level-request/index.md) | [androidJvm]<br>sealed class [CalculateSignalLevelRequest](-calculate-signal-level-request/index.md)<br>A set of classes and objects that are used to represent requests to calculate the number of signal strength bars based on the RSSI level of a network. |
| [CalculateSignalLevelResult](-calculate-signal-level-result/index.md) | [androidJvm]<br>sealed class [CalculateSignalLevelResult](-calculate-signal-level-result/index.md)<br>A set of classes and objects that are used to represent a result while calculating the number of signal bars based on an RSSI level. |
| [CompareSignalLevelRequest](-compare-signal-level-request/index.md) | [androidJvm]<br>data class [CompareSignalLevelRequest](-compare-signal-level-request/index.md)(val rssi1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val rssi2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>A data representation of a request to compare the RSSI level of two networks. |
| [CompareSignalLevelResult](-compare-signal-level-result/index.md) | [androidJvm]<br>sealed class [CompareSignalLevelResult](-compare-signal-level-result/index.md)<br>A set of classes and objects that are used to represent a result while comparing the RSSI values of two networks. |
