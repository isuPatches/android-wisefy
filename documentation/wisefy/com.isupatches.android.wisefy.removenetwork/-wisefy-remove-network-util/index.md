//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.removenetwork](../index.md)/[WisefyRemoveNetworkUtil](index.md)

# WisefyRemoveNetworkUtil

[androidJvm]\
internal class [WisefyRemoveNetworkUtil](index.md)(**coroutineDispatcherProvider**: [CoroutineDispatcherProvider](../../com.isupatches.android.wisefy.util.coroutines/-coroutine-dispatcher-provider/index.md), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)?, **savedNetworkUtil**: [SavedNetworkUtil](../../com.isupatches.android.wisefy.savednetworks/-saved-network-util/index.md), **sdkUtil**: [SdkUtil](../../com.isupatches.android.wisefy.util/-sdk-util/index.md), **wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [RemoveNetworkUtil](../-remove-network-util/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [removeNetwork](remove-network.md) | [androidJvm]<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>open override fun [removeNetwork](remove-network.md)(ssidToRemove: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [RemoveNetworkResult](../../com.isupatches.android.wisefy.removenetwork.entities/-remove-network-result/index.md)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(allOf = [android.permission.ACCESS_FINE_LOCATION, android.permission.CHANGE_WIFI_STATE])<br>open override fun [removeNetwork](remove-network.md)(ssidToRemove: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), callbacks: [RemoveNetworkCallbacks](../../com.isupatches.android.wisefy.callbacks/-remove-network-callbacks/index.md)?) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [coroutineDispatcherProvider](coroutine-dispatcher-provider.md) | [androidJvm]<br>private val [coroutineDispatcherProvider](coroutine-dispatcher-provider.md): [CoroutineDispatcherProvider](../../com.isupatches.android.wisefy.util.coroutines/-coroutine-dispatcher-provider/index.md) |
| [delegate](delegate.md) | [androidJvm]<br>private val [delegate](delegate.md): [RemoveNetworkApi](../-remove-network-api/index.md) |
| [removeNetworkScope](remove-network-scope.md) | [androidJvm]<br>private val [removeNetworkScope](remove-network-scope.md): CoroutineScope |
