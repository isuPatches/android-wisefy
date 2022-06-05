//[core](../../index.md)/[com.isupatches.android.wisefy.core.util](index.md)/[getNetwork](get-network.md)

# getNetwork

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)

fun [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html).[getNetwork](get-network.md)(): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?

A function that will retrieve a network from ConnectivityManager.

#### Return

WifiInfo or null - The network from ConnectivityManager or null if cannot be retrieved

#### Author

Patches Klinefelter

#### Since

03/2022
