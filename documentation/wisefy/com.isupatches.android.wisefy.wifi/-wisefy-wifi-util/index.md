//[wisefy](../../../index.md)/[com.isupatches.android.wisefy.wifi](../index.md)/[WisefyWifiUtil](index.md)

# WisefyWifiUtil

[androidJvm]\
internal class [WisefyWifiUtil](index.md)(**coroutineDispatcherProvider**: [CoroutineDispatcherProvider](../../com.isupatches.android.wisefy.util.coroutines/-coroutine-dispatcher-provider/index.md), **logger**: [WisefyLogger](../../com.isupatches.android.wisefy.shared.logging/-wisefy-logger/index.md)?, **sdkUtil**: [SdkUtil](../../com.isupatches.android.wisefy.util/-sdk-util/index.md), **wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [WifiUtil](../-wifi-util/index.md)

## Functions

| Name | Summary |
|---|---|
| [disableWifi](disable-wifi.md) | [androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~disableWifi~~](disable-wifi.md)~~(~~~~)~~~~:~~ [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>~~open~~ ~~override~~ ~~fun~~ [~~disableWifi~~](disable-wifi.md)~~(~~~~callbacks~~~~:~~ [DisableWifiCallbacks](../../com.isupatches.android.wisefy.callbacks/-disable-wifi-callbacks/index.md)?~~)~~ |
| [enableWifi](enable-wifi.md) | [androidJvm]<br>~~open~~ ~~override~~ ~~fun~~ [~~enableWifi~~](enable-wifi.md)~~(~~~~)~~~~:~~ [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>~~open~~ ~~override~~ ~~fun~~ [~~enableWifi~~](enable-wifi.md)~~(~~~~callbacks~~~~:~~ [EnableWifiCallbacks](../../com.isupatches.android.wisefy.callbacks/-enable-wifi-callbacks/index.md)?~~)~~ |
| [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596) | [androidJvm]<br>open operator fun [equals](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#585090901%2FFunctions%2F1622544596)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [hashCode](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1794629105%2FFunctions%2F1622544596)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isWifiEnabled](is-wifi-enabled.md) | [androidJvm]<br>open override fun [isWifiEnabled](is-wifi-enabled.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596) | [androidJvm]<br>open fun [toString](../../com.isupatches.android.wisefy.wifi.delegates/-legacy-wifi-delegate/index.md#1616463040%2FFunctions%2F1622544596)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [coroutineDispatcherProvider](coroutine-dispatcher-provider.md) | [androidJvm]<br>private val [coroutineDispatcherProvider](coroutine-dispatcher-provider.md): [CoroutineDispatcherProvider](../../com.isupatches.android.wisefy.util.coroutines/-coroutine-dispatcher-provider/index.md) |
| [delegate](delegate.md) | [androidJvm]<br>private val [delegate](delegate.md): [WifiApi](../-wifi-api/index.md) |
| [wifiScope](wifi-scope.md) | [androidJvm]<br>private val [wifiScope](wifi-scope.md): CoroutineScope |
