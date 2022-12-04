## Wisefy Documentation

- [:accesspoints package](#accesspoints-package)
  - [getNearbyAccessPoints](#getnearbyaccesspoints)
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
- [:wifi package](#wifi-package)
  - [disableWifi](#disablewifi)
  - [enabledWifi](#enablewifi)
- [Mutex Locks](#mutex-locks)

### `:accesspoints` package

#### getNearbyAccessPoints()

Gets nearby access points

- Usage example: [MiscViewModel::getNearbyAccessPoints](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L397)
- Request class: [GetNearbyAccessPointsQuery](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetNearbyAccessPointsQuery.kt)
- Result class: [GetNearbyAccessPointsResult](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetNearbyAccessPointsResult.kt)
- API options:
  - *Synchronous*: `fun getNearbyAccessPoints(query: GetNearbyAccessPointsQuery): GetNearbyAccessPointsResult`
  - *Async*: `fun getNearbyAccessPoints(query: GetNearbyAccessPointsQuery, callbacks: GetNearbyAccessPointCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getNearbyAccessPointsAsync(query: GetNearbyAccessPointsQuery): GetNearbyAccessPointsResult`
- Notes:
  - This can be leveraged to search for a single access point with `.first()`/`.firstOrNull()`
  - This can be leveraged for SSIDs with `.map { it.value.SSID }` or `.value.SSID`
  - This can be leveraged for BSSIs with `.map { it.value.BSSID }` or `.value.BSSID`

### `:addnetwork` package

#### addNetwork()

Adds a network to the saved list of configurations / suggestions

- Usage example: [AddNetworkViewModel::addNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/add/AddNetworkViewModel.kt#L186)
- Request class: [AddNetworkRequest](/wisefy/addnetwork/src/main/java/com/isupatches/android/wisefy/addnetwork/entities/AddNetworkRequest.kt)
- Result class: [AddNetworkResult](/wisefy/addnetwork/src/main/java/com/isupatches/android/wisefy/addnetwork/entities/AddNetworkResult.kt)
- API options:
  - *Synchronous*: `fun addNetwork(request: AddNetworkRequest): AddNetworkRestult`
  - *Async*: `fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.addNetworkAsync(request: AddNetworkRequest): AddNetworkResult`
- Notes: Currently supports Open and WPA2 network types along with WPA3 starting with Android Q

#### `:networkconnection` package

    - Connecting to a network
    - Disconnecting from the current network

### `:networkinfo` package

#### getCurrentNetwork()

Gets the device's current network

- Usage example: [MiscViewModel::getCurrentNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L256)
- Request class: [GetCurrentNetworkQuery](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkQuery.kt)
- Result class: [GetCurrentNetworkResult](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkResult.kt)
- API options:
  - *Synchronous*: `fun getCurrentNetwork(query: GetCurrentNetworkQuery): GetCurrentNetworkResult`
  - *Async*: `fun getCurrentNetwork(query: GetCurrentNetworkQuery, callbacks: GetCurrentNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getCurrentNetworkAsync(query: GetCurrentNetworkQuery): GetCurrentNetworkResult`

#### getNetworkConnectionStatus()

Gets the device's network connection status

- Usage example: [MiscViewModel::getNetworkConnectionStatus](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L431)
- Request class: [GetNetworkConnectionStatusQuery](/wisefy/networkconnectionstatus/src/main/java/com/isupatches/android/wisefy/networkconnectionstatus/entities/GetNetworkConnectionStatusQuery.kt)
- Result class: [GetNetworkConnectionStatusResult](/wisefy/networkconnectionstatus/src/main/java/com/isupatches/android/wisefy/networkconnectionstatus/entities/GetNetworkConnectionStatusResult.kt)
- API options:
  - *Synchronous*: `fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult`
  - *Async*: `fun getNetworkConnectionStatus(query: GetNetworkConnectionStatusQuery, callbacks: GetNetworkConnectionStatusCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getNetworkConnectionStatusAsync(query: GetNetworkConnectionStatusQuery): GetNetworkConnectionStatusResult`
  
#### `:removenetwork` package

#### removeNetwork()

Removes a network from the saved suggestions / configurations

- Usage example: [RemoveNetworkViewModel::removeNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/remove/RemoveNetworkViewModel.kt#L92)
- Request class: [RemoveNetworkRequest](/wisefy/removenetwork/src/main/java/com/isupatches/android/wisefy/removenetwork/entities/RemoveNetworkRequest.kt)
- Result class: [RemoveNetworkResult](/wisefy/removenetwork/src/main/java/com/isupatches/android/wisefy/removenetwork/entities/RemoveNetworkResult.kt)
- API options:
  - *Synchronous*: `fun removeNetwork(request: RemoveNetworkRequest): RemoveNetworkResult`
  - *Async*: `fun removeNetwork(request: RemoveNetworkRequest, callbacks: RemoveNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.removeNetworkAsync(request: RemoveNetworkRequest): RemoveNetworkResult`

### `:savednetworks` package

#### getSavedNetworks()

Gets the saved networks on a device

- Usage example: [MiscViewModel::getSavedNetworks](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L496)
- Request class: [GetSavedNetworksQuery](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/GetSavedNetworksQuery.kt)
- Result class: [GetSavedNetworksResult](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/GetSavedNetworksResult.kt)
- API options:
  - *Synchronous*: `fun getSavedNetworks(query: GetSavedNetworksQuery): GetSavedNetworksResult`
  - *Async*: `fun getSavedNetworks(query: GetSavedNetworksQuery, callbacks: GetSavedNetworksCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getSavedNetworksAsync(query: GetSavedNetworksQuery): GetSavedNetworksResult`
- Notes:
  - This can be leveraged to search for a single access point with `.first()`/`.firstOrNull()`

#### isNetworkSaved()

Checking if a specific network is saved

### `:sginal` package
  
  - Calculating signal strength bars
  - Comparing signal strength

### `:wifi` package

#### disableWifi()

Disables Wifi

- Usage example: [MiscViewModel::disableWifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L120)
- Request class: [DisableWifiRequest](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/DisableWifiRequest.kt)
- Result class: [DisableWifiResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/DisableWifiResult.kt)
- API options:
    - *Synchronous*: `fun disableWifi(request: DisableWifiRequest): DisableWifiResult`
    - *Async*: `fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?)`
    - *Coroutine*: `suspend fun WisefyApi.disableWifiAsync(request: DisableWifiRequest): DisableWifiResult`
- Notes:
  - Starting at Android Q (SDK 29), this will take the user to the Wifi Settings screen

#### enableWifi()

Enables Wifi

- Usage example: [MiscViewModel::enableWifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L215)
- Request class: [EnableWifiRequest](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/EnableWifiRequest.kt)
- Result class: [EnableWifiResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/EnableWifiResult.kt)
- API options:
  - *Synchronous*: `fun enableWifi(request: EnableWifiRequest): EnableWifiResult`
  - *Async*: `fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.enableWifiAsync(request: EnableWifiRequest): EnableWifiResult`
- Notes:
  - Starting at Android Q (SDK 29), this will take the user to the Wifi Settings screen

### Mutex Locks

- networkConnectionMutex: `connectToNetwork`, `disconnectFromCurrentNetwork`, `getCurrentNetwork`, and 
    `getNetworkConnectionStatus`
- savedNetworkMutex: `addNetwork`, `getSavedNetworks`, `isNetworkSaved`, and `removeNetwork`
- wifiMutex: Locks `enableWifi`, `disableWifi`, and `isWifiEnabled`
