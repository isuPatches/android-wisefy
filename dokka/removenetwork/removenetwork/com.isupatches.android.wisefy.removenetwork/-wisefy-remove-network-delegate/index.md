//[removenetwork](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[WisefyRemoveNetworkDelegate](index.md)

# WisefyRemoveNetworkDelegate

[androidJvm]\
class [WisefyRemoveNetworkDelegate](index.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), savedNetworkDelegate: [SavedNetworkDelegate](../../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks/-saved-network-delegate/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [RemoveNetworkDelegate](../-remove-network-delegate/index.md)

An internal Wisefy delegate for removing a network through the Android OS.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.core.coroutines.CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md) |  |
| [com.isupatches.android.wisefy.savednetworks.SavedNetworkDelegate](../../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks/-saved-network-delegate/index.md) |  |
| [com.isupatches.android.wisefy.core.util.SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) |  |
| [com.isupatches.android.wisefy.core.logging.WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| coroutineDispatcherProvider | The instance of the coroutine dispatcher provider to use |
| scope | The coroutine scope to use |
| logger | The logger instance to use |
| savedNetworkDelegate | The SavedNetworkDelegate instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |

## Constructors

| | |
|---|---|
| [WisefyRemoveNetworkDelegate](-wisefy-remove-network-delegate.md) | [androidJvm]<br>fun [WisefyRemoveNetworkDelegate](-wisefy-remove-network-delegate.md)(coroutineDispatcherProvider: [CoroutineDispatcherProvider](../../../../core/core/com.isupatches.android.wisefy.core.coroutines/-coroutine-dispatcher-provider/index.md), scope: CoroutineScope, logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), savedNetworkDelegate: [SavedNetworkDelegate](../../../../savednetworks/savednetworks/com.isupatches.android.wisefy.savednetworks/-saved-network-delegate/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>An synchronous API to remove a network.<br>[androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [&quot;android.permission.ACCESS_FINE_LOCATION&quot;, &quot;android.permission.CHANGE_WIFI_STATE&quot;])<br>open override fun [removeNetwork](remove-network.md)(request: [RemoveNetworkRequest](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-request/index.md), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.removenetwork.callbacks/-remove-network-callbacks/index.md)?)<br>An asynchronous API to remove a network. |
