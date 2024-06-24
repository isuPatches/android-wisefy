//[signal](../../../index.md)/[com.isupatches.android.wisefy.signal](../index.md)/[WisefySignalDelegate](index.md)/[WisefySignalDelegate](-wisefy-signal-delegate.md)

# WisefySignalDelegate

[androidJvm]\
constructor(assertions: WisefyAssertions, logger: WisefyLogger, sdkUtil: SdkUtil, wifiManager: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), adapter: [SignalApi](../-signal-api/index.md) = if (sdkUtil.isAtLeastR()) {
        Android30SignalAdapter(wifiManager, logger, assertions)
    } else {
        DefaultSignalAdapter(logger, assertions)
    })

#### Parameters

androidJvm

| | |
|---|---|
| assertions | The WisefyAssertions instance to use |
| logger | The WisefyLogger instance to use |
| sdkUtil | The SdkUtil instance to use |
| wifiManager | The WifiManager instance to use |
| adapter | The adapter instance to use for signal strength operations (determined based on the Android OS level) |
