## Wisefy Documentation

- [:accesspoints package](#accesspoints-package)
  - [getAccessPoints](#getaccesspoints)
- [:addnetwork package](#addnetwork-package)
  - [addNetwork](#addnetwork)
- [:networkconnection package](#networkconnection-package)
  - [connectToNetwork](#connectToNetwork)
  - [disconnectFromCurrentNetwork](#disconnectFromCurrentNetwork)
- [:networkinfo package](#networkinfo-package)
  - [getCurrentNetwork](#getcurrentnetwork)
  - [getNetworkConnectionStatus](#getnetworkconnectionstatus)
- [:removenetwork package](#removenetwork-package)
  - [removeNetwork](#removenetwork)
- [:savednetworks package](#savednetworks-package)
  - [getSavedNetworks](#getsavednetworks) 
- [:signal package](#sginal-package)
  - [calculateSignalLevel](#calculatesignallevel)
  - [compareSignalLevel](#comparesignallevel)
- [:wifi package](#wifi-package)
  - [disableWifi](#disablewifi)
  - [enabledWifi](#enablewifi)
  - [isWifiEnabled](#iswifienabled)
- [Mutex Locks](#mutex-locks)

### `:accesspoints` package

#### getAccessPoints()

Gets nearby access points

- Usage example: [MiscViewModel::getNearbyAccessPoints](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L211)
- Request class: [GetAccessPointsQuery](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetAccessPointsQuery.kt)
- Result class: [GetAccessPointsResult](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetAccessPointsResult.kt)
- Callbacks: [GetAccessPointsCallbacks](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/callbacks/GetAccessPointsCallbacks.kt)
- API options:
  - *Synchronous*: `fun getAccessPoints(query: GetAccessPointsQuery): GetAccessPointsResult`
  - *Async*: `fun getAccessPoints(query: GetAccessPointsQuery, callbacks: GetAccessPointsCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getAccessPointsAsync(query: GetAccessPointsQuery): GetAccessPointsResult`
- Permissions: ACCESS_FINE_LOCATION
- Notes:
  - This can be leveraged to search for a single access point with `.first()`/`.firstOrNull()`
  - This can be leveraged for SSIDs with `.map { it.value.SSID }` or `.value.SSID`
  - This can be leveraged for BSSIs with `.map { it.value.BSSID }` or `.value.BSSID`

### `:addnetwork` package

#### addNetwork()

Adds a network to the saved list of configurations / suggestions

- Usage example: [AddNetworkViewModel::addNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/add/AddNetworkViewModel.kt#L357)
- Request class: [AddNetworkRequest](/wisefy/addnetwork/src/main/java/com/isupatches/android/wisefy/addnetwork/entities/AddNetworkRequest.kt)
- Result class: [AddNetworkResult](/wisefy/addnetwork/src/main/java/com/isupatches/android/wisefy/addnetwork/entities/AddNetworkResult.kt)
- Callbacks: [AddNetworkCallbacks](/wisefy/addnetwork/src/main/java/com/isupatches/android/wisefy/addnetwork/callbacks/AddNetworkCallbacks.kt)
- API options:
  - *Synchronous*: `fun addNetwork(request: AddNetworkRequest): AddNetworkRestult`
  - *Async*: `fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.addNetworkAsync(request: AddNetworkRequest): AddNetworkResult`
- Permissions: ACCESS_FINE_LOCATION and CHANGE_WIFI_STATE
- Notes: Currently supports Open and WPA2 network types along with WPA3 starting with Android Q

#### `:networkconnection` package

    - Connecting to a network
    - Disconnecting from the current network

### `:networkinfo` package

#### getCurrentNetwork()

Gets the device's current network

- Usage example: [MiscViewModel::getCurrentNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L182)
- Request class: [GetCurrentNetworkQuery](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkQuery.kt)
- Result class: [GetCurrentNetworkResult](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkResult.kt)
- Callbacks: [GetCurrentNetworkCallbacks](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/callbacks/GetCurrentNetworkCallbacks.kt)
- API options:
  - *Synchronous*: `fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult`
  - *Async*: `fun getCurrentNetwork(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getCurrentNetworkAsync(query: GetCurrentNetworkQuery): GetCurrentNetworkResult`
- Permissions: ACCESS_NETWORK_STATE

#### getNetworkConnectionStatus()

Gets the device's network connection status

- Usage example: [MiscViewModel::getNetworkConnectionStatus](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L245)
- Request class: [GetNetworkConnectionStatusQuery](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetNetworkConnectionStatusQuery.kt)
- Result class: [GetNetworkConnectionStatusResult](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetNetworkConnectionStatusResult.kt)
- Callbacks: [GetNetworkConnectionStatusCallbacks](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/callbacks/GetNetworkConnectionStatusCallbacks.kt)
- API options:
  - *Synchronous*: `fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult`
  - *Async*: `fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery, callbacks: GetNetworkConnectionStatusCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getNetworkConnectionStatusAsync(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult`
- Permissions: ACCESS_NETWORK_STATE

#### `:removenetwork` package

#### removeNetwork()

Removes a network from the saved suggestions / configurations

- Usage example: [RemoveNetworkViewModel::removeNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/remove/RemoveNetworkViewModel.kt#L92)
- Request class: [RemoveNetworkRequest](/wisefy/removenetwork/src/main/java/com/isupatches/android/wisefy/removenetwork/entities/RemoveNetworkRequest.kt)
- Result class: [RemoveNetworkResult](/wisefy/removenetwork/src/main/java/com/isupatches/android/wisefy/removenetwork/entities/RemoveNetworkResult.kt)
- Callbacks: [RemoveNetworkCallbacks](/wisefy/removenetwork/src/main/java/com/isupatches/android/wisefy/removenetwork/callbacks/RemoveNetworkCallbacks.kt)
- API options:
  - *Synchronous*: `fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult`
  - *Async*: `fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.removeNetworkAsync(request: RemoveNetworkRequest): RemoveNetworkResult`
- Permissions: ACCESS_FINE_LOCATION, ACCESS_WIFI_STATE, and CHANGE_WIFI_STATE

### `:savednetworks` package

#### getSavedNetworks()

Gets the saved networks on a device

- Usage example: [MiscViewModel::getSavedNetworks](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L275)
- Request class: [GetSavedNetworksQuery](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/GetSavedNetworksQuery.kt)
- Result class: [GetSavedNetworksResult](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/GetSavedNetworksResult.kt)
- Callbacks: [GetSavedNetworksCallbacks](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/callbacks/GetSavedNetworksCallbacks.kt)
- API options:
  - *Synchronous*: `fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult`
  - *Async*: `fun getSavedNetworks(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getSavedNetworksAsync(query: GetSavedNetworksQuery): GetSavedNetworksResult`
- Permissions: ACCESS_FINE_LOCATION and ACCESS_WIFI_STATE
- Notes:
  - This can be leveraged to search for a single access point with `.first()`/`.firstOrNull()`

#### isNetworkSaved()

For checking if a specific network is saved

- Usage example: todo@patches
- Query class: [IsNetworkSavedQuery](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/IsNetworkSavedQuery.kt)
- Result class: [IsNetworkSavedResult](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/IsNetworkSavedResult.kt)
- Callbacks: [IsNetworkSavedCallbacks](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/callbacks/IsNetworkSavedCallbacks.kt)
- API options:
  - *Synchronous*: `fun isNetworkSaved(query: IsNetworkSavedQuery): IsNetworkSavedResult`
  - *Async*: `fun isNetworkSaved(query: IsNetworkSavedQuery, callbacks: IsNetworkSavedCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.isNetworkSavedAsync(query: IsNetworkSavedQuery): IsNetworkSavedResult`
- Permissions: ACCESS_FINE_LOCATION and ACCESS_WIFI_STATE

### `:sginal` package

#### calculateSignalLevel()

Calculates the strength given an RSSI level.

- Usage example: [SignalViewModel::calculateSignalLevel](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/signal/SignalViewModel.kt#L105)
- Request class: [CalculateSignalLevelRequest](/wisefy/signal/src/main/java/com/isupatches/android/wisefy/signal/entities/CalculateSignalLevelRequest.kt)
- Result class: [CalculateSignalLevelResult](/wisefy/signal/src/main/java/com/isupatches/android/wisefy/signal/entities/CalculateSignalLevelResult.kt)
- API options:
  - *Synchronous*: `fun calculateSignalLevel(request: CalculateSignalLevelRequest): CalculateSignalLevelResult`
- Permissions: None

#### compareSignalLevel()

Compares the strength of two RSSI levels.

- Usage example: [SignalViewModel::compareSignalLevel](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/signal/SignalViewModel.kt#L153)
- Request class: [CompareSignalLevelRequest](/wisefy/signal/src/main/java/com/isupatches/android/wisefy/signal/entities/CompareSignalLevelRequest.kt)
- Result class: [CompareSignalLevelResult](/wisefy/signal/src/main/java/com/isupatches/android/wisefy/signal/entities/CompareSignalLevelResult.kt)
- API options:
  - *Synchronous*: `fun compareSignalLevel(request: CompareSignalLevelRequest): CompareSignalLevelResult`
- Permissions: None

### `:wifi` package

#### disableWifi()

Disables Wifi

- Usage example: [MiscViewModel::disableWifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L378)
- Request class: [DisableWifiRequest](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/DisableWifiRequest.kt)
- Result class: [DisableWifiResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/DisableWifiResult.kt)
- Callbacks: [DisableWifiCallbacks](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/callbacks/DisableWifiCallbacks.kt)
- API options:
    - *Synchronous*: `fun disableWifi(request: DisableWifiRequest): DisableWifiResult`
    - *Async*: `fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?)`
    - *Coroutine*: `suspend fun WisefyApi.disableWifiAsync(request: DisableWifiRequest): DisableWifiResult`
- Permissions: CHANGE_WIFI_STATE
- Notes:
  - Starting at Android Q (SDK 29), this will take the user to the Wifi Settings screen

#### enableWifi()

Enables Wifi

- Usage example: [MiscViewModel::enableWifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L413)
- Request class: [EnableWifiRequest](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/EnableWifiRequest.kt)
- Result class: [EnableWifiResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/EnableWifiResult.kt)
- Callbacks: [DisableWifiCallbacks](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/callbacks/EnableWifiCallbacks.kt)
- API options:
  - *Synchronous*: `fun enableWifi(request: EnableWifiRequest): EnableWifiResult`
  - *Async*: `fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.enableWifiAsync(request: EnableWifiRequest): EnableWifiResult`
- Permissions: CHANGE_WIFI_STATE
- Notes:
  - Starting at Android Q (SDK 29), this will take the user to the Wifi Settings screen

#### isWifiEnabled()

Returns if Wifi is enabled

- Usage example: [MiscViewModel::isWifiEnabled](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L309)
- Query class: [IsWifiEnabledQuery](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/IsWifiEnabledQuery.kt)
- Result class: [IsWifiEnabledResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/IsWifiEnabledResult.kt)
- Callbacks: [IsWifiEnabledCallbacks](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/callbacks/IsWifiEnabledCallbacks.kt)
- API options:
  - *Synchronous*: `fun isWifiEnabled(query: IsWifiEnabledQuery): IsWifiEnabledResult`
  - *Async*: `fun isWifiEnabled(query: IsWifiEnabledQuery, callbacks: IsWifiEnabledCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.isWifiEnabledAsync(query: IsWifiEnabledQuery): IsWifiEnabledResult`
- Permissions: ACCESS_WIFI_STATE
  
### Mutex Locks

- networkConnectionMutex: `connectToNetwork`, `disconnectFromCurrentNetwork`, `getCurrentNetwork`, and 
    `getNetworkConnectionStatus`
- savedNetworkMutex: `addNetwork`, `getSavedNetworks`, `isNetworkSaved`, and `removeNetwork`
- wifiMutex: Locks `enableWifi`, `disableWifi`, and `isWifiEnabled`
