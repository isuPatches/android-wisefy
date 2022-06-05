//[networkinfo](../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](index.md)

# Package com.isupatches.android.wisefy.networkinfo.entities

## Types

| Name | Summary |
|---|---|
| [GetCurrentNetworkRequest](-get-current-network-request/index.md) | [androidJvm]<br>class [GetCurrentNetworkRequest](-get-current-network-request/index.md)<br>A data representation of a request to get the device's current network. |
| [GetCurrentNetworkResult](-get-current-network-result/index.md) | [androidJvm]<br>sealed class [GetCurrentNetworkResult](-get-current-network-result/index.md)<br>A set of classes and objects that are used to represent results for getting the device's current network. |
| [GetIPRequest](-get-i-p-request/index.md) | [androidJvm]<br>class [GetIPRequest](-get-i-p-request/index.md)<br>A data representation of a request to get the device's IP address. |
| [GetIPResult](-get-i-p-result/index.md) | [androidJvm]<br>sealed class [GetIPResult](-get-i-p-result/index.md)<br>A set of classes and objects that are used to represent results for getting the device's IP address. |
| [GetNetworkInfoRequest](-get-network-info-request/index.md) | [androidJvm]<br>data class [GetNetworkInfoRequest](-get-network-info-request/index.md)(val network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null)<br>A data representation of a request to get information for a network. |
| [GetNetworkInfoResult](-get-network-info-result/index.md) | [androidJvm]<br>sealed class [GetNetworkInfoResult](-get-network-info-result/index.md)<br>A set of classes and objects that are used to represent results for getting information for a network. |
| [IPData](-i-p-data/index.md) | [androidJvm]<br>data class [IPData](-i-p-data/index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>A data representation of the device's IP from Android OS level returns. |
| [NetworkData](-network-data/index.md) | [androidJvm]<br>data class [NetworkData](-network-data/index.md)(val value: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html))<br>A data representation of the current network from Android OS level returns. |
| [NetworkInfoData](-network-info-data/index.md) | [androidJvm]<br>data class [NetworkInfoData](-network-info-data/index.md)(val capabilities: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)?, val linkProperties: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)?)<br>A data representation of the current network info from Android OS level returns. |
