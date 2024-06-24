//[wisefy](../../../index.md)/[com.isupatches.android.wisefy](../index.md)/[Wisefy](index.md)

# Wisefy

class [Wisefy](index.md) : [WisefyApi](../-wisefy-api/index.md)

The private constructor used by [Brains](-brains/index.md) to create a Wisefy instance.

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| accessPointsDelegate | The AccessPointsDelegate instance to use |
| addNetworkDelegate | The AddNetworkDelegate instance to use |
| networkConnectionDelegate | The NetworkConnectionDelegate instance to use |
| networkInfoDelegate | The NetworkInfoDelegate instance to use |
| removeNetworkDelegate | The RemoveNetworkDelegate instance to use |
| savedNetworkDelegate | The SavedNetworkDelegate instance to use |
| signalDelegate | The SignalDelegate instance to use |
| wifiDelegate | The WifiDelegate instance to use |
| scope | The CoroutineScope to use for async operations |
| connectivityManager | The ConnectivityManager instance to use |
| networkConnectionMutex | The mutex for network connection operations |
| logger | The WisefyLogger instance to use |

#### See also

| |
|---|
| AccessPointsDelegate |
| AddNetworkDelegate |
| NetworkConnectionDelegate |
| NetworkInfoDelegate |
| RemoveNetworkDelegate |
| SignalDelegate |
| WifiDelegate |
| [WisefyApi](../-wisefy-api/index.md) |
| WisefyLogger |

## Types

| Name | Summary |
|---|---|
| [Brains](-brains/index.md) | [androidJvm]<br>class [Brains](-brains/index.md)@[JvmOverloads](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-overloads/index.html)constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), throwOnAssertions: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, logger: WisefyLogger = DefaultWisefyLogger())<br>The public builder to create a Wisefy instance. |

## Functions

| Name | Summary |
|---|---|
| [addNetwork](add-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [addNetwork](add-network.md)(request: AddNetworkRequest): AddNetworkResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [addNetwork](add-network.md)(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?) |
| [calculateSignalLevel](calculate-signal-level.md) | [androidJvm]<br>open override fun [calculateSignalLevel](calculate-signal-level.md)(request: CalculateSignalLevelRequest): CalculateSignalLevelResult |
| [changeNetwork](change-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [changeNetwork](change-network.md)(request: ChangeNetworkRequest): ChangeNetworkResult<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>open override fun [changeNetwork](change-network.md)(request: ChangeNetworkRequest, callbacks: ChangeNetworkCallbacks?) |
| [compareSignalLevel](compare-signal-level.md) | [androidJvm]<br>open override fun [compareSignalLevel](compare-signal-level.md)(request: CompareSignalLevelRequest): CompareSignalLevelResult |
| [connectToNetwork](connect-to-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>open override fun [~~connectToNetwork~~](connect-to-network.md)(request: ConnectToNetworkRequest): ConnectToNetworkResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.ACCESS_NETWORK_STATE&quot;])<br>open override fun [~~connectToNetwork~~](connect-to-network.md)(request: ConnectToNetworkRequest, callbacks: ConnectToNetworkCallbacks?) |
| [disableWifi](disable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [disableWifi](disable-wifi.md)(request: DisableWifiRequest): DisableWifiResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [disableWifi](disable-wifi.md)(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?) |
| [disconnectFromCurrentNetwork](disconnect-from-current-network.md) | [androidJvm]<br>open override fun [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)(request: DisconnectFromCurrentNetworkRequest): DisconnectFromCurrentNetworkResult<br>open override fun [~~disconnectFromCurrentNetwork~~](disconnect-from-current-network.md)(request: DisconnectFromCurrentNetworkRequest, callbacks: DisconnectFromCurrentNetworkCallbacks?) |
| [dump](dump.md) | [androidJvm]<br>open override fun [dump](dump.md)()<br>The cleanup function for Wisefy.  This is recommended to be called in the `onDestroy` of the activity for the application. |
| [enableWifi](enable-wifi.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [enableWifi](enable-wifi.md)(request: EnableWifiRequest): EnableWifiResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.CHANGE_WIFI_STATE&quot;)<br>open override fun [enableWifi](enable-wifi.md)(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?) |
| [equals](../-wisefy-api/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../-wisefy-api/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAccessPoints](get-access-points.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getAccessPoints](get-access-points.md)(query: GetAccessPointsQuery): GetAccessPointsResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_FINE_LOCATION&quot;)<br>open override fun [getAccessPoints](get-access-points.md)(query: GetAccessPointsQuery, callbacks: GetAccessPointsCallbacks?) |
| [getCurrentNetwork](get-current-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getCurrentNetwork](get-current-network.md)(query: GetCurrentNetworkQuery): GetCurrentNetworkResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getCurrentNetwork](get-current-network.md)(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?) |
| [getNetworkConnectionStatus](get-network-connection-status.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [getNetworkConnectionStatus](get-network-connection-status.md)(query: GetNetworkConnectionStatusQuery, callbacks: GetNetworkConnectionStatusCallbacks?) |
| [getSavedNetworks](get-saved-networks.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [getSavedNetworks](get-saved-networks.md)(query: GetSavedNetworksQuery): GetSavedNetworksResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [getSavedNetworks](get-saved-networks.md)(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?) |
| [hashCode](../-wisefy-api/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../-wisefy-api/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [init](init.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>open override fun [init](init.md)()<br>The initialization function for Wisefy.  This is recommended to be called in the `onCreate` of the activity for the application. |
| [isNetworkSaved](is-network-saved.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [isNetworkSaved](is-network-saved.md)(query: IsNetworkSavedQuery): IsNetworkSavedResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])<br>open override fun [isNetworkSaved](is-network-saved.md)(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?) |
| [isWifiEnabled](is-wifi-enabled.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>open override fun [isWifiEnabled](is-wifi-enabled.md)(query: IsWifiEnabledQuery): IsWifiEnabledResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_WIFI_STATE&quot;)<br>open override fun [isWifiEnabled](is-wifi-enabled.md)(query: IsWifiEnabledQuery, callbacks: IsWifiEnabledCallbacks?) |
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [removeNetwork](remove-network.md)(request: RemoveNetworkRequest): RemoveNetworkResult<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [removeNetwork](remove-network.md)(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?) |
| [toString](../-wisefy-api/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../-wisefy-api/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
