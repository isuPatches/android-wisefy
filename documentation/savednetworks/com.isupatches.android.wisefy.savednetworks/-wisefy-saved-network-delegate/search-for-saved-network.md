//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[WisefySavedNetworkDelegate](index.md)/[searchForSavedNetwork](search-for-saved-network.md)

# searchForSavedNetwork

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

open override fun [searchForSavedNetwork](search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md)): [SearchForSavedNetworkResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-result/index.md)

A synchronous API to search for a saved network.

#### Return

SearchForSavedNetworkResult - The result of searching for a saved network

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a saved network |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

open override fun [searchForSavedNetwork](search-for-saved-network.md)(request: [SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md), callbacks: [SearchForSavedNetworkCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-network-callbacks/index.md)?)

An asynchronous API to search for a single saved network.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworkRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-network-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworkCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-network-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a single saved network |
| callbacks | The callbacks for when the result of searching for a single saved network is returned |
