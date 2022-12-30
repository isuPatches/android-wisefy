//[signal](../../index.md)/[com.isupatches.android.wisefy.signal](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [SignalApi](-signal-api/index.md) | [androidJvm]<br>interface [SignalApi](-signal-api/index.md)<br>A set of synchronous APIs for signal strength functionality. |
| [SignalDelegate](-signal-delegate/index.md) | [androidJvm]<br>interface [SignalDelegate](-signal-delegate/index.md) : [SignalApi](-signal-api/index.md)<br>A delegate for synchronous signal strength APIs. |
| [WisefySignalDelegate](-wisefy-signal-delegate/index.md) | [androidJvm]<br>class [WisefySignalDelegate](-wisefy-signal-delegate/index.md)(assertions: [WisefyAssertions](../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), adapter: [SignalApi](-signal-api/index.md) = if (sdkUtil.isAtLeastR()) {         Android30SignalAdapter(wifiManager, logger, assertions)     } else {         DefaultSignalAdapter(logger, assertions)     }) : [SignalDelegate](-signal-delegate/index.md)<br>An internal Wisefy delegate for signal strength functionality. |
