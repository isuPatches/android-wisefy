//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[WisefySignalDelegate](index.md)/[WisefySignalDelegate](-wisefy-signal-delegate.md)

# WisefySignalDelegate

[androidJvm]\
fun [WisefySignalDelegate](-wisefy-signal-delegate.md)(assertions: [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md), logger: [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md), sdkUtil: [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md), wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), adapter: [SignalApi](../-signal-api/index.md) = if (sdkUtil.isAtLeastR()) {
        Android30SignalAdapter(wifiManager, logger, assertions)
    } else {
        DefaultSignalAdapter(logger, assertions)
    })

#### Parameters

androidJvm

| | |
|---|---|
| assertions | The [WisefyAssertions](../../../../core/core/com.isupatches.android.wisefy.core.assertions/-wisefy-assertions/index.md) instance to use |
| logger | The [WisefyLogger](../../../../core/core/com.isupatches.android.wisefy.core.logging/-wisefy-logger/index.md) instance to use |
| sdkUtil | The [SdkUtil](../../../../core/core/com.isupatches.android.wisefy.core.util/-sdk-util/index.md) instance to use |
| wifiManager | The WifiManager instance to use |
