//[savednetworks](../../../index.md)/[com.isupatches.android.wisefy.savednetworks](../index.md)/[WisefySavedNetworkDelegate](index.md)/[searchForSavedNetworks](search-for-saved-networks.md)

# searchForSavedNetworks

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

open override fun [searchForSavedNetworks](search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md)): [SearchForSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/index.md)

A synchronous API to search for a set of saved networks.

#### Return

SearchForSavedNetworksResult - The result of searching for a set of saved networks

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksResult](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-result/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a set of saved networks |

[androidJvm]\

@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.ACCESS_WIFI_STATE&quot;])

open override fun [searchForSavedNetworks](search-for-saved-networks.md)(request: [SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md), callbacks: [SearchForSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-networks-callbacks/index.md)?)

An asynchronous API to search for a set of saved networks.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.savednetworks.entities.SearchForSavedNetworksRequest](../../com.isupatches.android.wisefy.savednetworks.entities/-search-for-saved-networks-request/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.callbacks.SearchForSavedNetworksCallbacks](../../com.isupatches.android.wisefy.savednetworks.callbacks/-search-for-saved-networks-callbacks/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| request | The details of the request to search for a set of saved networks |
| callbacks | The callbacks for when the result of searching for a set of saved networks is returned |
