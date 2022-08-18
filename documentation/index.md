## Wisefy Documentation

#### `:accesspoints`

  - [Getting nearby access points](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#getNearbyAccessPoints)
    - Synchronous option: `fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult`
    - Async option: `fun getNearbyAccessPoints(request: GetNearbyAccessPointsRequest, callbacks: GetNearbyAccessPointCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.getNearbyAccessPointsAsync(request: GetNearbyAccessPointsRequest): GetNearbyAccessPointsResult`
    
  - [Getting the RSSI level of an access point](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#getRSSI)
    - Synchronous option: `fun getRSSI(request: GetRSSIRequest): GetRSSIResult`
    - Async option: `fun getRSSI(request: GetRSSIRequest, callbacks: GetRSSICallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.getRSSIAsync(request: GetRSSIRequest): GetRSSIResult`

  - [Searching for a single access point](/documentation/accesspoints/searching-for-a-single-access-point.md)
  - [Searching for multiple access points](/documentation/accesspoints/searching-for-multiple-access-points.md)
  - [Searching for a single SSID/BSSID](/documentation/accesspoints/searching-for-a-single-ssid-or-bssid.md)
  - [Searching for multiple SSIDs/BSSIDs](/documentation/accesspoints/searching-for-multiple-ssids-or-bssids.md)

#### `:addnetwork`

  - [Adding a network](/app/src/main/java/com/isupatches/android/wisefy/sample/features/add/AddNetworkViewModel.kt#addNetwork)
    - Synchronous option: `fun addNetwork(request: AddNetworkRequest): AddNetworkRestult`
    - Async option: `fun addNetwork(request: AddNetworkRequest, callbacks: AddNetworkCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.addNetworkAsync(request: AddNetworkRequest): AddNetworkResult`
    - Notes: Currently supports Open and WPA2 network types along with WPA3 starting with Android Q
    
#### `:frequency`

  - [Getting the frequency of a network](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#getFrequency)
    - Synchronous option: `fun getFrequency(request: GetFrequencyRequest): GetFrequencyResult`
    - Async option: `fun getFrequency(request: GetFrequencyRequest, callbacks: GetFrequencyCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.getFrequencyAsync(request: GetFrequencyRequest): GetFrequencyResult`
    
  - [Checking if a network is 5gHz](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#isNetwork5gHz)
    - Synchronous option: `fun isNetwork5gHz(request: IsNetwork5gHzRequest): IsNetwork5gHzResult`
    - Async option: `fun isNetwork5gHz(request: IsNetwork5gHzRequest, callbacks: IsNetwork5gHzCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.isNetwork5gHzAsync(request: IsNetwork5gHzRequest): IsNetwork5gHzResult`

#### `:networkconnection`

    - Connecting to a network
    - Disconnecting from the current network

#### `:networkconnectionstatus`

    - Checking if the device is connected to a mobile network
    - Checking if the device is connected to a mobile or wifi network
    - Checking if the device is connected to a given SSID/BSSID
    - Checking if the device is connected to a wifi network
    - Checking if the device is roaming

#### `:networkinfo`

  - [Getting the device's current network](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#getCurrentNetwork)
    - Synchronous option: `fun getCurrentNetwork(request: GetCurrentNetworkRequest): GetCurrentNetworkResult`
    - Async option: `fun getCurrentNetwork(request: GetCurrentNetworkRequest, callbacks: GetCurrentNetworkCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.getCurrentNetworkAsync(request: GetCurrentNetworkRequest): GetCurrentNetworkResult`

  - [Getting the device's current network info](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#getCurrentNetworkInfo)
    - Synchronous option: `fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult`
    - Async option: `fun getCurrentNetworkInfo(request: GetCurrentNetworkInfoRequest, callbacks: GetCurrentNetworkInfoCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.getCurrentNetworkInfoAsync(request: GetCurrentNetworkInfoRequest): GetCurrentNetworkInfoResult`

  - [Getting the device's IP](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#getIP)
    - Synchronous option: `fun getIP(request: GetIPRequest): GetIPResult`
    - Async option: `fun getIP(request: GetIPRequest, callbacks: GetIPCallbacks?)`
    - Coroutine option: `suspend fun WisefyApi.getIPAsync(request: GetIPRequest): GetIPResult`
    
#### `:removenetwork`

    - Removing a network

#### `:savednetworks`

    - Getting the saved networks on a device
    - Checking if a specific network is saved
    - Searching for a single saved network
    - Searching for multiple saved networks

#### `:security`

    - Checking if the network contains a specific security capability
    - Checking if the network is secure

#### `:sginal`

    - Calculating signal strength bars
    - Comparing signal strength

####`:wifi`

    - [Enabling Wifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#enableWifi) **DEPRECATED**
    - [Disabling Wifi](/app/src/main/java/com/isupatches/android/wisefy/sample/features/misc/MiscViewModel.kt#disableWifi) **DEPRECATED**
