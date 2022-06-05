//[wisefy](../../../index.md)/[com.isupatches.android.wisefy](../index.md)/[WisefyApi](index.md)

# WisefyApi

[androidJvm]\
interface [WisefyApi](index.md) : AccessPointsApi, AccessPointsApiAsync, AddNetworkApi, AddNetworkApiAsync, FrequencyApi, FrequencyApiAsync, NetworkConnectionApi, NetworkConnectionApiAsync, NetworkConnectionStatusApi, NetworkInfoApi, NetworkInfoApiAsync, RemoveNetworkApi, RemoveNetworkApiAsync, SavedNetworkApi, SavedNetworkApiAsync, SecurityApi, SignalApi, WifiApi, WifiApiAsync

The culmination of APIs that create WiseFy's public interface.

#### Author

Patches Klinefelter

#### Since

07/2021

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.accesspoints.AccessPointsApi |  |
| com.isupatches.android.wisefy.accesspoints.AccessPointsApiAsync |  |
| com.isupatches.android.wisefy.addnetwork.AddNetworkApi |  |
| com.isupatches.android.wisefy.addnetwork.AddNetworkApiAsync |  |
| com.isupatches.android.wisefy.frequency.FrequencyApi |  |
| com.isupatches.android.wisefy.frequency.FrequencyApiAsync |  |
| com.isupatches.android.wisefy.networkconnection.NetworkConnectionApi |  |
| com.isupatches.android.wisefy.networkconnection.NetworkConnectionApiAsync |  |
| com.isupatches.android.wisefy.networkconnectionstatus.NetworkConnectionStatusApi |  |
| com.isupatches.android.wisefy.networkinfo.NetworkInfoApi |  |
| com.isupatches.android.wisefy.networkinfo.NetworkInfoApiAsync |  |
| com.isupatches.android.wisefy.removenetwork.RemoveNetworkApi |  |
| com.isupatches.android.wisefy.removenetwork.RemoveNetworkApiAsync |  |
| com.isupatches.android.wisefy.savednetworks.SavedNetworkApi |  |
| com.isupatches.android.wisefy.savednetworks.SavedNetworkApiAsync |  |
| com.isupatches.android.wisefy.security.SecurityApi |  |
| com.isupatches.android.wisefy.signal.SignalApi |  |
| com.isupatches.android.wisefy.wifi.WifiApi |  |
| com.isupatches.android.wisefy.wifi.WifiApiAsync |  |

## Functions

| Name | Summary |
|---|---|
| [addOpenNetwork](index.md#829724428%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [addOpenNetwork](index.md#829724428%2FFunctions%2F1622544596)(request: AddOpenNetworkRequest): AddNetworkResult<br>abstract fun [addOpenNetwork](index.md#268539376%2FFunctions%2F1622544596)(request: AddOpenNetworkRequest, callbacks: AddNetworkCallbacks?) |
| [addWPA2Network](index.md#-1079727476%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [addWPA2Network](index.md#-1079727476%2FFunctions%2F1622544596)(request: AddWPA2NetworkRequest): AddNetworkResult<br>abstract fun [addWPA2Network](index.md#1447700848%2FFunctions%2F1622544596)(request: AddWPA2NetworkRequest, callbacks: AddNetworkCallbacks?) |
| [addWPA3Network](index.md#1247723530%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [addWPA3Network](index.md#1247723530%2FFunctions%2F1622544596)(request: AddWPA3NetworkRequest): AddNetworkResult<br>abstract fun [addWPA3Network](index.md#-1462570770%2FFunctions%2F1622544596)(request: AddWPA3NetworkRequest, callbacks: AddNetworkCallbacks?) |
| [calculateBars](index.md#1899008359%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [calculateBars](index.md#1899008359%2FFunctions%2F1622544596)(request: CalculateBarsRequest): CalculateBarsResult |
| [compareSignalLevel](index.md#-388573905%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [compareSignalLevel](index.md#-388573905%2FFunctions%2F1622544596)(request: CompareSignalLevelRequest): CompareSignalLevelResult |
| [connectToNetwork](index.md#343712830%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [connectToNetwork](index.md#343712830%2FFunctions%2F1622544596)(request: NetworkConnectionRequest): NetworkConnectionResult<br>abstract fun [connectToNetwork](index.md#-116039239%2FFunctions%2F1622544596)(request: NetworkConnectionRequest, callbacks: ConnectToNetworkCallbacks?) |
| [disableWifi](index.md#-964430246%2FFunctions%2F1622544596) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](index.md#-964430246%2FFunctions%2F1622544596)~~(~~request: DisableWifiRequest~~)~~~~:~~ DisableWifiResult<br>~~abstract~~ ~~fun~~ [~~disableWifi~~](index.md#1032310224%2FFunctions%2F1622544596)~~(~~request: DisableWifiRequest, callbacks: DisableWifiCallbacks?~~)~~ |
| [disconnectFromCurrentNetwork](index.md#-582868122%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [disconnectFromCurrentNetwork](index.md#-582868122%2FFunctions%2F1622544596)(): NetworkConnectionResult<br>abstract fun [disconnectFromCurrentNetwork](index.md#1484246476%2FFunctions%2F1622544596)(callbacks: DisconnectFromCurrentNetworkCallbacks?) |
| [doesNetworkContainSecurityCapability](index.md#-183727609%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [doesNetworkContainSecurityCapability](index.md#-183727609%2FFunctions%2F1622544596)(request: ContainsSecurityCapabilityRequest): ContainsSecurityCapabilityResult |
| [dump](dump.md) | [androidJvm]<br>abstract fun [dump](dump.md)() |
| [enableWifi](index.md#1766140350%2FFunctions%2F1622544596) | [androidJvm]<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](index.md#1766140350%2FFunctions%2F1622544596)~~(~~request: EnableWifiRequest~~)~~~~:~~ EnableWifiResult<br>~~abstract~~ ~~fun~~ [~~enableWifi~~](index.md#112615167%2FFunctions%2F1622544596)~~(~~request: EnableWifiRequest, callbacks: EnableWifiCallbacks?~~)~~ |
| [equals](index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCurrentNetwork](index.md#640483159%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getCurrentNetwork](index.md#640483159%2FFunctions%2F1622544596)(request: GetCurrentNetworkRequest): GetCurrentNetworkResult<br>abstract fun [getCurrentNetwork](index.md#-95913880%2FFunctions%2F1622544596)(request: GetCurrentNetworkRequest, callbacks: GetCurrentNetworkCallbacks?) |
| [getFrequency](index.md#-1548932533%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getFrequency](index.md#-1548932533%2FFunctions%2F1622544596)(request: GetFrequencyRequest): GetFrequencyResult<br>abstract fun [getFrequency](index.md#884437139%2FFunctions%2F1622544596)(request: GetFrequencyRequest, callbacks: GetFrequencyCallbacks?) |
| [getIP](index.md#80779771%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getIP](index.md#80779771%2FFunctions%2F1622544596)(request: GetIPRequest): GetIPResult<br>abstract fun [getIP](index.md#888651322%2FFunctions%2F1622544596)(request: GetIPRequest, callbacks: GetIPCallbacks?) |
| [getNearbyAccessPoints](index.md#396147240%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getNearbyAccessPoints](index.md#396147240%2FFunctions%2F1622544596)(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult<br>abstract fun [getNearbyAccessPoints](index.md#747516892%2FFunctions%2F1622544596)(request: GetNearbyAccessPointsRequest, callbacks: GetNearbyAccessPointCallbacks?) |
| [getNetworkInfo](index.md#961784363%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getNetworkInfo](index.md#961784363%2FFunctions%2F1622544596)(request: GetNetworkInfoRequest): GetNetworkInfoResult<br>abstract fun [getNetworkInfo](index.md#79779027%2FFunctions%2F1622544596)(request: GetNetworkInfoRequest, callbacks: GetNetworkInfoCallbacks?) |
| [getRSSI](index.md#-525189944%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getRSSI](index.md#-525189944%2FFunctions%2F1622544596)(request: GetRSSIRequest): GetRSSIResult<br>abstract fun [getRSSI](index.md#-439584%2FFunctions%2F1622544596)(request: GetRSSIRequest, callbacks: GetRSSICallbacks?) |
| [getSavedNetworks](index.md#781741366%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getSavedNetworks](index.md#781741366%2FFunctions%2F1622544596)(request: GetSavedNetworksRequest): GetSavedNetworksResult<br>abstract fun [getSavedNetworks](index.md#-92529730%2FFunctions%2F1622544596)(request: GetSavedNetworksRequest, callbacks: GetSavedNetworksCallbacks?) |
| [hashCode](index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [init](init.md) | [androidJvm]<br>abstract fun [init](init.md)() |
| [isDeviceConnectedToMobileNetwork](index.md#-1838605483%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileNetwork](index.md#-1838605483%2FFunctions%2F1622544596)(): IsDeviceConnectedResult |
| [isDeviceConnectedToMobileOrWifiNetwork](index.md#-1638258035%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isDeviceConnectedToMobileOrWifiNetwork](index.md#-1638258035%2FFunctions%2F1622544596)(): IsDeviceConnectedResult |
| [isDeviceConnectedToSSID](index.md#826833273%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isDeviceConnectedToSSID](index.md#826833273%2FFunctions%2F1622544596)(request: IsDeviceConnectedToSSIDRequest): IsDeviceConnectedResult |
| [isDeviceConnectedToWifiNetwork](index.md#206843592%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isDeviceConnectedToWifiNetwork](index.md#206843592%2FFunctions%2F1622544596)(): IsDeviceConnectedResult |
| [isDeviceRoaming](index.md#249878788%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isDeviceRoaming](index.md#249878788%2FFunctions%2F1622544596)(): IsDeviceRoamingResult |
| [isNetwork5gHz](index.md#481643249%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isNetwork5gHz](index.md#481643249%2FFunctions%2F1622544596)(request: IsNetwork5gHzRequest): IsNetwork5gHzResult |
| [isNetworkSaved](index.md#743526550%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isNetworkSaved](index.md#743526550%2FFunctions%2F1622544596)(request: IsNetworkSavedRequest): IsNetworkSavedResult |
| [isNetworkSecure](index.md#329938159%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isNetworkSecure](index.md#329938159%2FFunctions%2F1622544596)(request: IsNetworkSecureRequest): IsNetworkSecureResult |
| [isWifiEnabled](index.md#945470458%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isWifiEnabled](index.md#945470458%2FFunctions%2F1622544596)(request: IsWifiEnabledRequest): IsWifiEnabledResult |
| [removeNetwork](index.md#368164523%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [removeNetwork](index.md#368164523%2FFunctions%2F1622544596)(request: RemoveNetworkRequest): RemoveNetworkResult<br>abstract fun [removeNetwork](index.md#-132636185%2FFunctions%2F1622544596)(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) |
| [searchForAccessPoint](index.md#-1505244082%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [searchForAccessPoint](index.md#-1505244082%2FFunctions%2F1622544596)(request: SearchForSingleAccessPointRequest): SearchForAccessPointResult<br>abstract fun [searchForAccessPoint](index.md#2033067662%2FFunctions%2F1622544596)(request: SearchForSingleAccessPointRequest, callbacks: SearchForAccessPointCallbacks?) |
| [searchForAccessPoints](index.md#728574424%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [searchForAccessPoints](index.md#728574424%2FFunctions%2F1622544596)(request: SearchForMultipleAccessPointsRequest): SearchForAccessPointsResult<br>abstract fun [searchForAccessPoints](index.md#-1734312939%2FFunctions%2F1622544596)(request: SearchForMultipleAccessPointsRequest, callbacks: SearchForAccessPointsCallbacks?) |
| [searchForSavedNetwork](index.md#-740792964%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [searchForSavedNetwork](index.md#-740792964%2FFunctions%2F1622544596)(request: SearchForSavedNetworkRequest): SearchForSavedNetworkResult<br>abstract fun [searchForSavedNetwork](index.md#-1906414262%2FFunctions%2F1622544596)(request: SearchForSavedNetworkRequest, callbacks: SearchForSavedNetworkCallbacks?) |
| [searchForSavedNetworks](index.md#319101334%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [searchForSavedNetworks](index.md#319101334%2FFunctions%2F1622544596)(request: SearchForSavedNetworksRequest): SearchForSavedNetworksResult<br>abstract fun [searchForSavedNetworks](index.md#-1235886135%2FFunctions%2F1622544596)(request: SearchForSavedNetworksRequest, callbacks: SearchForSavedNetworksCallbacks?) |
| [searchForSSID](index.md#-1317994416%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [searchForSSID](index.md#-1317994416%2FFunctions%2F1622544596)(request: SearchForSingleSSIDRequest): SearchForSSIDResult<br>abstract fun [searchForSSID](index.md#410289065%2FFunctions%2F1622544596)(request: SearchForSingleSSIDRequest, callbacks: SearchForSSIDCallbacks?) |
| [searchForSSIDs](index.md#765493278%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [searchForSSIDs](index.md#765493278%2FFunctions%2F1622544596)(request: SearchForMultipleSSIDsRequest): SearchForSSIDsResult<br>abstract fun [searchForSSIDs](index.md#-170326286%2FFunctions%2F1622544596)(request: SearchForMultipleSSIDsRequest, callbacks: SearchForSSIDsCallbacks?) |
| [toString](index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inheritors

| Name |
|---|
| [Wisefy](../-wisefy/index.md) |
