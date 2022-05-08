//[wisefy](../../../index.md)/[com.isupatches.android.wisefy](../index.md)/[WisefyApi](index.md)

# WisefyApi

[androidJvm]\
interface [WisefyApi](index.md) : [AccessPointsApi](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/index.md), [AccessPointsApiAsync](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/index.md), [AddNetworkApi](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/index.md), [AddNetworkApiAsync](../../com.isupatches.android.wisefy.addnetwork/-add-network-api-async/index.md), [FrequencyApi](../../com.isupatches.android.wisefy.frequency/-frequency-api/index.md), [FrequencyApiAsync](../../com.isupatches.android.wisefy.frequency/-frequency-api-async/index.md), [NetworkConnectionApi](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api/index.md), [NetworkConnectionApiAsync](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api-async/index.md), [NetworkConnectionStatusApi](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/index.md), [NetworkInfoApi](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/index.md), [NetworkInfoApiAsync](../../com.isupatches.android.wisefy.networkinfo/-network-info-api-async/index.md), [RemoveNetworkApi](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api/index.md), [RemoveNetworkApiAsync](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api-async/index.md), [SavedNetworkApi](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/index.md), [SavedNetworkApiAsync](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api-async/index.md), [SecurityApi](../../com.isupatches.android.wisefy.security/-security-api/index.md), [SignalApi](../../com.isupatches.android.wisefy.signal/-signal-api/index.md), [WifiApi](../../com.isupatches.android.wisefy.wifi/-wifi-api/index.md), [WifiApiAsync](../../com.isupatches.android.wisefy.wifi/-wifi-api-async/index.md)

The culmination of APIs that create WiseFy's public interface.

#### Author

Patches Klinefelter

#### Since

07/2021

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.AccessPointsApi](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/index.md) |  |
| [com.isupatches.android.wisefy.accesspoints.AccessPointsApiAsync](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/index.md) |  |
| [AddNetworkApi](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/index.md) |  |
| [AddNetworkApiAsync](../../com.isupatches.android.wisefy.addnetwork/-add-network-api-async/index.md) |  |
| [com.isupatches.android.wisefy.frequency.FrequencyApi](../../com.isupatches.android.wisefy.frequency/-frequency-api/index.md) |  |
| [com.isupatches.android.wisefy.frequency.FrequencyApiAsync](../../com.isupatches.android.wisefy.frequency/-frequency-api-async/index.md) |  |
| [NetworkConnectionApi](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api/index.md) |  |
| [NetworkConnectionApiAsync](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api-async/index.md) |  |
| [NetworkConnectionStatusApi](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/index.md) |  |
| [NetworkInfoApi](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/index.md) |  |
| [NetworkInfoApiAsync](../../com.isupatches.android.wisefy.networkinfo/-network-info-api-async/index.md) |  |
| [RemoveNetworkApi](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api/index.md) |  |
| [RemoveNetworkApiAsync](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api-async/index.md) |  |
| [SavedNetworkApi](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/index.md) |  |
| [SavedNetworkApiAsync](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api-async/index.md) |  |
| [SecurityApi](../../com.isupatches.android.wisefy.security/-security-api/index.md) |  |
| [SignalApi](../../com.isupatches.android.wisefy.signal/-signal-api/index.md) |  |
| [WifiApi](../../com.isupatches.android.wisefy.wifi/-wifi-api/index.md) |  |
| [WifiApiAsync](../../com.isupatches.android.wisefy.wifi/-wifi-api-async/index.md) |  |

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/add-open-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addOpenNetwork](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/add-open-network.md)(data: [OpenNetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-open-network-data/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addOpenNetwork](../../com.isupatches.android.wisefy.addnetwork/-add-network-api-async/add-open-network.md)(data: [OpenNetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-open-network-data/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-add-network-callbacks/index.md)?) |
| [addWPA2Network](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/add-w-p-a2-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA2Network](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/add-w-p-a2-network.md)(data: [WPA2NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a2-network-data/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA2Network](../../com.isupatches.android.wisefy.addnetwork/-add-network-api-async/add-w-p-a2-network.md)(data: [WPA2NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a2-network-data/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-add-network-callbacks/index.md)?) |
| [addWPA3Network](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/add-w-p-a3-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA3Network](../../com.isupatches.android.wisefy.addnetwork/-add-network-api/add-w-p-a3-network.md)(data: [WPA3NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a3-network-data/index.md)): [AddNetworkResult](../../com.isupatches.android.wisefy.addnetwork.entities/-add-network-result/index.md)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [addWPA3Network](../../com.isupatches.android.wisefy.addnetwork/-add-network-api-async/add-w-p-a3-network.md)(data: [WPA3NetworkData](../../com.isupatches.android.wisefy.addnetwork.entities/-w-p-a3-network-data/index.md), callbacks: [AddNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-add-network-callbacks/index.md)?) |
| [attachNetworkWatcher](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/attach-network-watcher.md) | [androidJvm]<br>abstract fun [attachNetworkWatcher](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/attach-network-watcher.md)() |
| [calculateBars](../../com.isupatches.android.wisefy.signal/-signal-api/calculate-bars.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>abstract fun [calculateBars](../../com.isupatches.android.wisefy.signal/-signal-api/calculate-bars.md)(rssiLevel: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>~~abstract~~ ~~fun~~ [~~calculateBars~~](../../com.isupatches.android.wisefy.signal/-signal-api/calculate-bars.md)~~(~~~~rssiLevel~~~~:~~ [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~,~~ ~~targetNumberOfBars~~~~:~~ [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)~~)~~~~:~~ [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [compareSignalLevel](../../com.isupatches.android.wisefy.signal/-signal-api/compare-signal-level.md) | [androidJvm]<br>abstract fun [compareSignalLevel](../../com.isupatches.android.wisefy.signal/-signal-api/compare-signal-level.md)(rssi1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), rssi2: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [connectToNetwork](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api/connect-to-network.md) | [androidJvm]<br>abstract fun [connectToNetwork](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api/connect-to-network.md)(ssidToConnectTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>abstract fun [connectToNetwork](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api-async/connect-to-network.md)(ssidToConnectTo: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callbacks: [ConnectToNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-connect-to-network-callbacks/index.md)?) |
| [detachNetworkWatcher](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/detach-network-watcher.md) | [androidJvm]<br>abstract fun [detachNetworkWatcher](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/detach-network-watcher.md)() |
| [disableWifi](../../com.isupatches.android.wisefy.wifi/-wifi-api/disable-wifi.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](../../com.isupatches.android.wisefy.wifi/-wifi-api/disable-wifi.md)~~(~~~~)~~~~:~~ [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](../../com.isupatches.android.wisefy.wifi/-wifi-api-async/disable-wifi.md)~~(~~~~callbacks~~~~:~~ [DisableWifiCallbacks](../../com.isupatches.android.wisefy.callbacks/-disable-wifi-callbacks/index.md)?~~)~~ |
| [disconnectFromCurrentNetwork](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api/disconnect-from-current-network.md) | [androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api/disconnect-from-current-network.md)(): [NetworkConnectionResult](../../com.isupatches.android.wisefy.networkconnection.entities/-network-connection-result/index.md)<br>abstract fun [disconnectFromCurrentNetwork](../../com.isupatches.android.wisefy.networkconnection/-network-connection-api-async/disconnect-from-current-network.md)(callbacks: [DisconnectFromCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-disconnect-from-current-network-callbacks/index.md)?) |
| [enableWifi](../../com.isupatches.android.wisefy.wifi/-wifi-api/enable-wifi.md) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](../../com.isupatches.android.wisefy.wifi/-wifi-api/enable-wifi.md)~~(~~~~)~~~~:~~ [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](../../com.isupatches.android.wisefy.wifi/-wifi-api-async/enable-wifi.md)~~(~~~~callbacks~~~~:~~ [EnableWifiCallbacks](../../com.isupatches.android.wisefy.callbacks/-enable-wifi-callbacks/index.md)?~~)~~ |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/get-current-network.md) | [androidJvm]<br>abstract fun [getCurrentNetwork](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/get-current-network.md)(): [CurrentNetworkData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-data/index.md)?<br>abstract fun [getCurrentNetwork](../../com.isupatches.android.wisefy.networkinfo/-network-info-api-async/get-current-network.md)(callbacks: [GetCurrentNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-current-network-callbacks/index.md)?) |
| [getCurrentNetworkInfo](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/get-current-network-info.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>abstract fun [getCurrentNetworkInfo](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/get-current-network-info.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null): [CurrentNetworkInfoData](../../com.isupatches.android.wisefy.networkinfo.entities/-current-network-info-data/index.md)?<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_NETWORK_STATE)<br>abstract fun [getCurrentNetworkInfo](../../com.isupatches.android.wisefy.networkinfo/-network-info-api-async/get-current-network-info.md)(callbacks: [GetCurrentNetworkInfoCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-current-network-info-callbacks/index.md)?, network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null) |
| [getFrequency](../../com.isupatches.android.wisefy.frequency/-frequency-api/get-frequency.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [getFrequency](../../com.isupatches.android.wisefy.frequency/-frequency-api/get-frequency.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [getFrequency](../../com.isupatches.android.wisefy.frequency/-frequency-api/get-frequency.md)(network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [getFrequency](../../com.isupatches.android.wisefy.frequency/-frequency-api-async/get-frequency.md)(callbacks: [GetFrequencyCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-frequency-callbacks/index.md)?) |
| [getIP](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/get-i-p.md) | [androidJvm]<br>abstract fun [getIP](../../com.isupatches.android.wisefy.networkinfo/-network-info-api/get-i-p.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>abstract fun [getIP](../../com.isupatches.android.wisefy.networkinfo/-network-info-api-async/get-i-p.md)(callbacks: [GetIPCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-i-p-callbacks/index.md)?) |
| [getNearbyAccessPoints](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/get-nearby-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [getNearbyAccessPoints](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/get-nearby-access-points.md)(filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)><br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [getNearbyAccessPoints](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/get-nearby-access-points.md)(filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), callbacks: [GetNearbyAccessPointCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-nearby-access-point-callbacks/index.md)?) |
| [getRSSI](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/get-r-s-s-i.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [getRSSI](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/get-r-s-s-i.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [getRSSI](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/get-r-s-s-i.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), takeHighest: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callbacks: [GetRSSICallbacks](../../com.isupatches.android.wisefy.callbacks/-get-r-s-s-i-callbacks/index.md)?) |
| [getSavedNetworks](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [getSavedNetworks](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/get-saved-networks.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)><br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [getSavedNetworks](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api-async/get-saved-networks.md)(callbacks: [GetSavedNetworksCallbacks](../../com.isupatches.android.wisefy.callbacks/-get-saved-networks-callbacks/index.md)?) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isDeviceConnectedToMobileNetwork](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-mobile-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileNetwork](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-mobile-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToMobileOrWifiNetwork](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-mobile-or-wifi-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileOrWifiNetwork](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-mobile-or-wifi-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToSSID](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-s-s-i-d.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToSSID](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-s-s-i-d.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceConnectedToWifiNetwork](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-wifi-network.md) | [androidJvm]<br>abstract fun [isDeviceConnectedToWifiNetwork](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-connected-to-wifi-network.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDeviceRoaming](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-roaming.md) | [androidJvm]<br>abstract fun [isDeviceRoaming](../../com.isupatches.android.wisefy.networkconnectionstatus/-network-connection-status-api/is-device-roaming.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetwork5gHz](../../com.isupatches.android.wisefy.frequency/-frequency-api/is-network5g-hz.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [isNetwork5gHz](../../com.isupatches.android.wisefy.frequency/-frequency-api/is-network5g-hz.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 21)<br>abstract fun [isNetwork5gHz](../../com.isupatches.android.wisefy.frequency/-frequency-api/is-network5g-hz.md)(network: [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkEAP](../../com.isupatches.android.wisefy.security/-security-api/is-network-e-a-p.md) | [androidJvm]<br>abstract fun [isNetworkEAP](../../com.isupatches.android.wisefy.security/-security-api/is-network-e-a-p.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkPSK](../../com.isupatches.android.wisefy.security/-security-api/is-network-p-s-k.md) | [androidJvm]<br>abstract fun [isNetworkPSK](../../com.isupatches.android.wisefy.security/-security-api/is-network-p-s-k.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkSaved](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [isNetworkSaved](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/is-network-saved.md)(ssid: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkSecure](../../com.isupatches.android.wisefy.security/-security-api/is-network-secure.md) | [androidJvm]<br>abstract fun [isNetworkSecure](../../com.isupatches.android.wisefy.security/-security-api/is-network-secure.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWEP](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-e-p.md) | [androidJvm]<br>abstract fun [isNetworkWEP](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-e-p.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWPA](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-p-a.md) | [androidJvm]<br>abstract fun [isNetworkWPA](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-p-a.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWPA2](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-p-a2.md) | [androidJvm]<br>abstract fun [isNetworkWPA2](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-p-a2.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNetworkWPA3](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-p-a3.md) | [androidJvm]<br>abstract fun [isNetworkWPA3](../../com.isupatches.android.wisefy.security/-security-api/is-network-w-p-a3.md)(scanResult: [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isWifiEnabled](../../com.isupatches.android.wisefy.wifi/-wifi-api/is-wifi-enabled.md) | [androidJvm]<br>abstract fun [isWifiEnabled](../../com.isupatches.android.wisefy.wifi/-wifi-api/is-wifi-enabled.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [removeNetwork](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api/remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [removeNetwork](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api/remove-network.md)(ssidToRemove: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>abstract fun [removeNetwork](../../com.isupatches.android.wisefy.removenetwork/-remove-network-api-async/remove-network.md)(ssidToRemove: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-remove-network-callbacks/index.md)?) |
| [searchForAccessPoint](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-access-point.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForAccessPoint](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-access-point.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)?<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForAccessPoint](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/search-for-access-point.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), callbacks: [SearchForAccessPointCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-access-point-callbacks/index.md)?) |
| [searchForAccessPoints](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForAccessPoints](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-access-points.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[AccessPointData](../../com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)><br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForAccessPoints](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/search-for-access-points.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), filterDuplicates: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), callbacks: [SearchForAccessPointsCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-access-points-callbacks/index.md)?) |
| [searchForSavedNetwork](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/search-for-saved-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [searchForSavedNetwork](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/search-for-saved-network.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)?<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [searchForSavedNetwork](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api-async/search-for-saved-network.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callbacks: [SearchForSavedNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-saved-network-callbacks/index.md)?) |
| [searchForSavedNetworks](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/search-for-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [searchForSavedNetworks](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api/search-for-saved-networks.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[SavedNetworkData](../../com.isupatches.android.wisefy.savednetworks.entities/-saved-network-data/index.md)><br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.ACCESS_WIFI_STATE])<br>abstract fun [searchForSavedNetworks](../../com.isupatches.android.wisefy.savednetworks/-saved-network-api-async/search-for-saved-networks.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callbacks: [SearchForSavedNetworksCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-saved-networks-callbacks/index.md)?) |
| [searchForSSID](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-s-s-i-d.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForSSID](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-s-s-i-d.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForSSID](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/search-for-s-s-i-d.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callbacks: [SearchForSSIDCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-s-s-i-d-callbacks/index.md)?) |
| [searchForSSIDs](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-s-s-i-ds.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForSSIDs](../../com.isupatches.android.wisefy.accesspoints/-access-points-api/search-for-s-s-i-ds.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)<[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)><br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = android.permission.ACCESS_FINE_LOCATION)<br>abstract fun [searchForSSIDs](../../com.isupatches.android.wisefy.accesspoints/-access-points-api-async/search-for-s-s-i-ds.md)(regexForSSID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callbacks: [SearchForSSIDsCallbacks](../../com.isupatches.android.wisefy.callbacks/-search-for-s-s-i-ds-callbacks/index.md)?) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Wisefy](../-wisefy/index.md) |