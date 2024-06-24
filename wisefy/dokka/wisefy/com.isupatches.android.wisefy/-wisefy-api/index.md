//[wisefy](../../../index.md)/[com.isupatches.android.wisefy](../index.md)/[WisefyApi](index.md)

# WisefyApi

interface [WisefyApi](index.md) : AccessPointsApi, AccessPointsApiAsync, AddNetworkApi, AddNetworkApiAsync, NetworkConnectionApi, NetworkConnectionApiAsync, NetworkInfoApi, NetworkInfoApiAsync, RemoveNetworkApi, RemoveNetworkApiAsync, SavedNetworkApi, SavedNetworkApiAsync, SignalApi, WifiApi, WifiApiAsync

The culmination of APIs that create Wisefy's public interface.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### See also

| |
|---|
| AccessPointsApi |
| AccessPointsApiAsync |
| AddNetworkApi |
| AddNetworkApiAsync |
| NetworkConnectionApi |
| NetworkConnectionApiAsync |
| NetworkInfoApi |
| NetworkInfoApiAsync |
| RemoveNetworkApi |
| RemoveNetworkApiAsync |
| SavedNetworkApi |
| SavedNetworkApiAsync |
| SignalApi |
| WifiApi |
| WifiApiAsync |

#### Inheritors

| |
|---|
| [Wisefy](../-wisefy/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addNetwork](index.md#261690720%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [addNetwork](index.md#261690720%2FFunctions%2F1622544596)(request: AddNetworkRequest): AddNetworkResult<br>abstract fun [addNetwork](index.md#-628744636%2FFunctions%2F1622544596)(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) |
| [calculateSignalLevel](index.md#-411865875%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [calculateSignalLevel](index.md#-411865875%2FFunctions%2F1622544596)(request: CalculateSignalLevelRequest): CalculateSignalLevelResult |
| [changeNetwork](index.md#1936550061%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [changeNetwork](index.md#1936550061%2FFunctions%2F1622544596)(request: ChangeNetworkRequest): ChangeNetworkResult<br>abstract fun [changeNetwork](index.md#-1732139141%2FFunctions%2F1622544596)(request: ChangeNetworkRequest, callbacks: ChangeNetworkCallbacks?) |
| [compareSignalLevel](index.md#-388573905%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [compareSignalLevel](index.md#-388573905%2FFunctions%2F1622544596)(request: CompareSignalLevelRequest): CompareSignalLevelResult |
| [connectToNetwork](index.md#-371751525%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [~~connectToNetwork~~](index.md#-371751525%2FFunctions%2F1622544596)(request: ConnectToNetworkRequest): ConnectToNetworkResult<br>abstract fun [~~connectToNetwork~~](index.md#-1622712176%2FFunctions%2F1622544596)(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) |
| [disableWifi](index.md#-964430246%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [disableWifi](index.md#-964430246%2FFunctions%2F1622544596)(request: DisableWifiRequest): DisableWifiResult<br>abstract fun [disableWifi](index.md#1032310224%2FFunctions%2F1622544596)(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) |
| [disconnectFromCurrentNetwork](index.md#-1811274917%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [~~disconnectFromCurrentNetwork~~](index.md#-1811274917%2FFunctions%2F1622544596)(request: DisconnectFromCurrentNetworkRequest): DisconnectFromCurrentNetworkResult<br>abstract fun [~~disconnectFromCurrentNetwork~~](index.md#242587774%2FFunctions%2F1622544596)(request: DisconnectFromCurrentNetworkRequest, callbacks: DisconnectFromCurrentNetworkCallbacks?) |
| [dump](dump.md) | [androidJvm]<br>abstract fun [dump](dump.md)()<br>The cleanup function for Wisefy.  This is recommended to be called in the `onDestroy` of the activity for the application. |
| [enableWifi](index.md#1766140350%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [enableWifi](index.md#1766140350%2FFunctions%2F1622544596)(request: EnableWifiRequest): EnableWifiResult<br>abstract fun [enableWifi](index.md#112615167%2FFunctions%2F1622544596)(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) |
| [equals](index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAccessPoints](index.md#-1670878481%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getAccessPoints](index.md#-1670878481%2FFunctions%2F1622544596)(query: GetAccessPointsQuery): GetAccessPointsResult<br>abstract fun [getAccessPoints](index.md#-794247639%2FFunctions%2F1622544596)(query: GetAccessPointsQuery, callbacks: GetAccessPointsCallbacks?) |
| [getCurrentNetwork](index.md#-30404034%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getCurrentNetwork](index.md#-30404034%2FFunctions%2F1622544596)(query: GetCurrentNetworkQuery): GetCurrentNetworkResult<br>abstract fun [getCurrentNetwork](index.md#713220815%2FFunctions%2F1622544596)(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?) |
| [getNetworkConnectionStatus](index.md#1001710418%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getNetworkConnectionStatus](index.md#1001710418%2FFunctions%2F1622544596)(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult<br>abstract fun [getNetworkConnectionStatus](index.md#1418981642%2FFunctions%2F1622544596)(query: GetNetworkConnectionStatusQuery, callbacks: GetNetworkConnectionStatusCallbacks?) |
| [getSavedNetworks](index.md#269183965%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [getSavedNetworks](index.md#269183965%2FFunctions%2F1622544596)(query: GetSavedNetworksQuery): GetSavedNetworksResult<br>abstract fun [getSavedNetworks](index.md#-444090377%2FFunctions%2F1622544596)(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?) |
| [hashCode](index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [init](init.md) | [androidJvm]<br>abstract fun [init](init.md)()<br>The initialization function for Wisefy.  This is recommended to be called in the `onCreate` of the activity for the application. |
| [isNetworkSaved](index.md#2070259517%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isNetworkSaved](index.md#2070259517%2FFunctions%2F1622544596)(query: IsNetworkSavedQuery): IsNetworkSavedResult<br>abstract fun [isNetworkSaved](index.md#-1100599766%2FFunctions%2F1622544596)(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?) |
| [isWifiEnabled](index.md#282762145%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [isWifiEnabled](index.md#282762145%2FFunctions%2F1622544596)(query: IsWifiEnabledQuery): IsWifiEnabledResult<br>abstract fun [isWifiEnabled](index.md#-1084498684%2FFunctions%2F1622544596)(query: IsWifiEnabledQuery, callbacks: IsWifiEnabledCallbacks?) |
| [removeNetwork](index.md#368164523%2FFunctions%2F1622544596) | [androidJvm]<br>abstract fun [removeNetwork](index.md#368164523%2FFunctions%2F1622544596)(request: RemoveNetworkRequest): RemoveNetworkResult<br>abstract fun [removeNetwork](index.md#-132636185%2FFunctions%2F1622544596)(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) |
| [toString](index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
