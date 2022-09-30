## Wisefy Documentation

- [accesspoints package](#accesspoints-package)
  - [getNearbyAccessPoints](#getnearbyaccesspoints)
  - [getRSSI](#getrssi)
  - [searchForAccessPoints](#searchforaccesspoints)
- [addnetwork package](#addnetwork-package)
  - [addNetwork](#addnetwork)
- [frequency package](#frequency-package)
  - [getFrequency](#getfrequency)
  - [isNetwork5gHz](#isnetwork5ghz)
- [networkconnection package](#networkconnection-package)
- [networkconnectionstatus package](#networkconnectionstatus-package)
  - [getNetworkConnectionStatus](#getnetworkconnectionstatus)
- [networkinfo package](#networkinfo-package)
  - [getCurrentNetwork](#getcurrentnetwork)
  - [getCurrentNetworkInfo](#getcurrentnetworkinfo)
- [removenetwork package](#removenetwork-package)
  - [getCurrentNetwork](#removenetwork)
- [savednetworks package](#savednetworks-package)
  - [getSavedNetworks](#getsavednetworks) 
  - [searchForSavedNetworks](#searchforsavednetworks) 
- [wifi package](#wifi-package)
  - [disableWifi](#disablewifi) **DEPRECATED**
  - [enabledWifi](#enablewifi) **DEPRECATED**
- [Mutex Locks](#mutex-locks)

### `:accesspoints` package

#### getNearbyAccessPoints()

Gets nearby access points

- Usage example: [MiscViewModel::getNearbyAccessPoints](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L397)
- Request class: [GetNearbyAccessPointsRequest](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetNearbyAccessPointsRequest.kt)
- Result class: [GetNearbyAccessPointsResult](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetNearbyAccessPointsResult.kt)
- API options:
  - *Synchronous*: `fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult`
  - *Async*: `fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest, callbacks: GetNearbyAccessPointCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getNearbyAccessPointsAsync(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult`

#### getRSSI()

Gets the RSSI level of an access point

- Usage example: [MiscViewModel::getRSSI](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L460)
- Request class: [GetRSSIRequest](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetRSSIRequest.kt)
- Result class: [GetRSSIResult](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/GetRSSIResult.kt)
- API options:
  - *Synchronous*: `fun getRSSI(request: GetRSSIRequest): GetRSSIResult`
  - *Async*: `fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getRSSIAsync(request: GetRSSIRequest): GetRSSIResult`

#### searchForAccessPoints()

Searches for access points that are nearby

- Usage example: [SearchViewModel::searchForAccessPoints](/app/src/main/java/com/isupatches/android/wisefy/sample/features/search/SearchViewModel.kt#L193)
- Request class: [SearchForAccessPointsRequest](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/SearchForAccessPointsRequest.kt)
- Result class: [SearchForAccessPointsResult](/wisefy/accesspoints/src/main/java/com/isupatches/android/wisefy/accesspoints/entities/SearchForAccessPointsResult.kt)
- API options:
  - *Synchronous*: `fun searchForAccessPoints(request: SearchForAccessPointsRequest): SearchForAccessPointsResult`
  - *Async*: `fun searchForAccessPoints(request: SearchForAccessPointsRequest, callbacks: SearchForAccessPointsCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.searchForAccessPointsAsync(request: SearchForAccessPointsRequest): SearchForAccessPointsResult`
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
    
### `:frequency` package

#### getFrequency()

Getting the frequency of a network

- Usage example: [MiscViewModel::getFrequency](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L326)
- Request class: [GetFrequencyRequest](/wisefy/frequency/src/main/java/com/isupatches/android/wisefy/frequency/entities/GetFrequencyRequest.kt)
- Result class: [GetFrequencyResult](/wisefy/frequency/src/main/java/com/isupatches/android/wisefy/frequency/entities/GetFrequencyResult.kt)
- API options:
  - *Synchronous*: `fun getFrequency(request: GetFrequencyRequest): GetFrequencyResult`
  - *Async*: `fun getFrequency(request: GetFrequencyRequest, callbacks: GetFrequencyCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getFrequencyAsync(request: GetFrequencyRequest): GetFrequencyResult`
    
#### isNetwork5gHz()    

Checks if a network is 5gHz

- Usage example: [MiscViewModel::isNetwork5gHz](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L541)
- Request class: [IsNetwork5gHzRequest](/wisefy/frequency/src/main/java/com/isupatches/android/wisefy/frequency/entities/IsNetwork5gHzRequest.kt)
- Result class: [IsNetwork5gHzResult](/wisefy/frequency/src/main/java/com/isupatches/android/wisefy/frequency/entities/IsNetwork5gHzResult.kt)
- API options:
  - *Synchronous*: `fun isNetwork5gHz(request: IsNetwork5gHzRequest): IsNetwork5gHzResult`
  - *Async*: `fun isNetwork5gHz(request: IsNetwork5gHzRequest, callbacks: IsNetwork5gHzCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.isNetwork5gHzAsync(request: IsNetwork5gHzRequest): IsNetwork5gHzResult`

#### `:networkconnection` package

    - Connecting to a network
    - Disconnecting from the current network

### `:networkconnectionstatus` package

#### getNetworkConnectionStatus()

Gets the device's network connection status
 
- Usage example: [MiscViewModel::getNetworkConnectionStatus](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L431)
- Request class: [GetNetworkConnectionStatusRequest](/wisefy/networkconnectionstatus/src/main/java/com/isupatches/android/wisefy/networkconnectionstatus/entities/GetNetworkConnectionStatusRequest.kt)
- Result class: [GetNetworkConnectionStatusResult](/wisefy/networkconnectionstatus/src/main/java/com/isupatches/android/wisefy/networkconnectionstatus/entities/GetNetworkConnectionStatusResult.kt)
- API options:
  - *Synchronous*: `fun getNetworkConnectionStatus(request: GetNetworkConnectionStatusRequest): GetNetworkConnectionStatusResult`
  - *Async*: `fun getNetworkConnectionStatus(request: GetNetworkConnectionStatusRequest = GetNetworkConnectionStatusRequest(), callbacks: GetNetworkConnectionStatusCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getNetworkConnectionStatusAsync(request: GetNetworkConnectionStatusRequest): GetNetworkConnectionStatusResult`

### `:networkinfo` package

#### getCurrentNetwork()

Gets the device's current network

- Usage example: [MiscViewModel::getCurrentNetwork](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L256)
- Request class: [GetCurrentNetworkRequest](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkRequest.kt)
- Result class: [GetCurrentNetworkRequest](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkResult.kt)
- API options:
  - *Synchronous*: `fun getCurrentNetwork(request: GetCurrentNetworkRequest): GetCurrentNetworkResult`
  - *Async*: `fun getCurrentNetwork(request: GetCurrentNetworkRequest, callbacks: GetCurrentNetworkCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getCurrentNetworkAsync(request: GetCurrentNetworkRequest): GetCurrentNetworkResult`

#### getCurrentNetworkInfo()

Gets the device's current network info

- Usage example: [MiscViewModel::getCurrentNetworkInfo](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L285)
- Request class: [GetCurrentNetworkInfoRequest](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkInfoRequest.kt)
- Result class: [GetCurrentNetworkInfoResult](/wisefy/networkinfo/src/main/java/com/isupatches/android/wisefy/networkinfo/entities/GetCurrentNetworkInfoResult.kt)
- API options:
  - Synchronous option: `fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult`
  - Async option: `fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest, callbacks: GetCurrentNetworkInfoCallbacks?)`
  - Coroutine option: `suspend fun WisefyApi.getCurrentNetworkInfoAsync(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult`
    
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
- Request class: [GetSavedNetworksRequest](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/GetSavedNetworksRequest.kt)
- Result class: [GetSavedNetworksResult](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/GetSavedNetworksResult.kt)
- API options:
  - *Synchronous*: `fun getSavedNetworks(request: GetSavedNetworksRequest): GetSavedNetworksResult`
  - *Async*: `fun getSavedNetworks(request: GetSavedNetworksRequest, callbacks: GetSavedNetworksCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.getSavedNetworksAsync(request: GetSavedNetworksRequest): GetSavedNetworksResult`

####

Checking if a specific network is saved

#### searchForSavedNetworks()

Searches for saved networks on the device

- Usage example: [SearchViewModel::searchForSavedNetworks](/app/src/main/java/com/isupatches/android/wisefy/sample/features/search/SearchViewModel.kt#L328)
- Request class: [SearchForSavedNetworksRequest](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/SearchForSavedNetworksRequest.kt)
- Result class: [SearchForSavedNetworksResult](/wisefy/savednetworks/src/main/java/com/isupatches/android/wisefy/savednetworks/entities/SearchForSavedNetworksResult.kt)
- API options:
    - *Synchronous*: `fun searchForSavedNetworks(request: SearchForSavedNetworksRequest): SearchForSavedNetworksResult`
    - *Async*: `fun searchForSavedNetworks(request: SearchForSavedNetworksRequest, callbacks: SearchForSavedNetworksCallbacks?)`
    - *Coroutine*: `suspend fun WisefyApi.searchForSavedNetworkAsync(request: SearchForSavedNetworksRequest): SearchForSavedNetworksResult`
  - Notes:
    - This can be leveraged to search for a single access point with `.first()`/`.firstOrNull()`

### `:sginal` package
  
  - Calculating signal strength bars
  - Comparing signal strength

### `:wifi` package

#### enableWifi()

Enables Wifi **[DEPRECATED]**

- Usage example: [MiscViewModel::enableWifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L215)
- Request class: [EnableWifiRequest](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/EnableWifiRequest.kt)
- Result class: [EnableWifiResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/EnableWifiResult.kt)
- API options:
  - *Synchronous*: `fun enableWifi(request: EnableWifiRequest): EnableWifiResult`
  - *Async*: `fun enableWifi(request: EnableWifiRequest, callbacks: EnableWifiCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.enableWifiAsync(request: EnableWifiRequest): EnableWifiResult`

#### disableWifi()

Disables Wifi **[DEPRECATED]**

- Usage example: [MiscViewModel::disableWifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#L120)
- Request class: [DisableWifiRequest](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/DisableWifiRequest.kt)
- Result class: [DisableWifiResult](/wisefy/wifi/src/main/java/com/isupatches/android/wisefy/wifi/entities/DisableWifiResult.kt)
- API options:
  - *Synchronous*: `fun disableWifi(request: DisableWifiRequest): DisableWifiResult`
  - *Async*: `fun disableWifi(request: DisableWifiRequest, callbacks: DisableWifiCallbacks?)`
  - *Coroutine*: `suspend fun WisefyApi.disableWifiAsync(request: DisableWifiRequest): DisableWifiResult`

### Mutex Locks

- networkConnectionMutex: `connectToNetwork`, `disconnectFromCurrentNetwork`, `getCurrentNetwork`,
    `getCurrentNetworkInfo`, `getNetworkConnectionStatus`, `getFrequency`, and `isNetwork5gHz`
- savedNetworkMutex: `addNetwork`, `getSavedNetworks`, `isNetworkSaved`, `removeNetwork`, and `searchForSavedNetworks`
- wifiMutex: Locks `enableWifi`, `disableWifi`, and `isWifiEnabled`
 