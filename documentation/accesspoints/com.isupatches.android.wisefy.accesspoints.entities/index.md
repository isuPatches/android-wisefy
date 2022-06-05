//[accesspoints](../../index.md)/[com.isupatches.android.wisefy.accesspoints.entities](index.md)

# Package com.isupatches.android.wisefy.accesspoints.entities

## Types

| Name | Summary |
|---|---|
| [AccessPointData](-access-point-data/index.md) | [androidJvm]<br>data class [AccessPointData](-access-point-data/index.md)(val value: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html))<br>A data representation of an Access Point from the Android OS. |
| [GetNearbyAccessPointsRequest](-get-nearby-access-points-request/index.md) | [androidJvm]<br>data class [GetNearbyAccessPointsRequest](-get-nearby-access-points-request/index.md)(val filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)<br>A data representation of a request to get all nearby access points. |
| [GetNearbyAccessPointsResult](-get-nearby-access-points-result/index.md) | [androidJvm]<br>sealed class [GetNearbyAccessPointsResult](-get-nearby-access-points-result/index.md)<br>A set of classes and objects that are used to represent a result when getting nearby access points. |
| [GetRSSIRequest](-get-r-s-s-i-request/index.md) | [androidJvm]<br>sealed class [GetRSSIRequest](-get-r-s-s-i-request/index.md)<br>A set of classes and objects that are used to represent requests to retrieve the RRSI level of a nearby access point. |
| [GetRSSIResult](-get-r-s-s-i-result/index.md) | [androidJvm]<br>sealed class [GetRSSIResult](-get-r-s-s-i-result/index.md)<br>A set of classes and objects that are used to represent a result when getting the RSSI of a nearby access point. |
| [RSSIData](-r-s-s-i-data/index.md) | [androidJvm]<br>data class [RSSIData](-r-s-s-i-data/index.md)(val value: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?)<br>A data representation of a network's RSSI level. |
| [SearchForAccessPointResult](-search-for-access-point-result/index.md) | [androidJvm]<br>sealed class [SearchForAccessPointResult](-search-for-access-point-result/index.md)<br>A set of classes and objects that are used to represent a result when searching for a nearby access point. |
| [SearchForAccessPointsResult](-search-for-access-points-result/index.md) | [androidJvm]<br>sealed class [SearchForAccessPointsResult](-search-for-access-points-result/index.md)<br>A set of classes and objects that are used to represent a result when searching for nearby access points. |
| [SearchForMultipleAccessPointsRequest](-search-for-multiple-access-points-request/index.md) | [androidJvm]<br>sealed class [SearchForMultipleAccessPointsRequest](-search-for-multiple-access-points-request/index.md)<br>A set of classes and objects that are used to represent requests to search for multiple access points. |
| [SearchForMultipleSSIDsRequest](-search-for-multiple-s-s-i-ds-request/index.md) | [androidJvm]<br>sealed class [SearchForMultipleSSIDsRequest](-search-for-multiple-s-s-i-ds-request/index.md)<br>A set of classes and objects that are used to represent requests to search for multiple SSIDs. |
| [SearchForSingleAccessPointRequest](-search-for-single-access-point-request/index.md) | [androidJvm]<br>sealed class [SearchForSingleAccessPointRequest](-search-for-single-access-point-request/index.md)<br>A set of classes and objects that are used to represent requests to search for a single access point. |
| [SearchForSingleSSIDRequest](-search-for-single-s-s-i-d-request/index.md) | [androidJvm]<br>sealed class [SearchForSingleSSIDRequest](-search-for-single-s-s-i-d-request/index.md)<br>A set of classes and objects that are used to represent requests to search for a single SSID. |
| [SearchForSSIDResult](-search-for-s-s-i-d-result/index.md) | [androidJvm]<br>sealed class [SearchForSSIDResult](-search-for-s-s-i-d-result/index.md)<br>A set of classes and objects that are used to represent a result when searching for a nearby SSID. |
| [SearchForSSIDsResult](-search-for-s-s-i-ds-result/index.md) | [androidJvm]<br>sealed class [SearchForSSIDsResult](-search-for-s-s-i-ds-result/index.md)<br>A set of classes and objects that are used to represent a result when searching for a nearby SSIDs. |
| [SSIDData](-s-s-i-d-data/index.md) | [androidJvm]<br>sealed class [SSIDData](-s-s-i-d-data/index.md)<br>A set of data representations for a networks SSID value. |
