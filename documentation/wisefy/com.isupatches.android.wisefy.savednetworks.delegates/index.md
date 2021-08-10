//[wisefy](../../index.md)/[com.isupatches.android.wisefy.savednetworks.delegates](index.md)

# Package com.isupatches.android.wisefy.savednetworks.delegates

## Types

| Name | Summary |
|---|---|
| [Android29SavedNetworkApi](-android29-saved-network-api/index.md) | [androidJvm]<br>internal interface [Android29SavedNetworkApi](-android29-saved-network-api/index.md) |
| [Android29SavedNetworkApiImpl](-android29-saved-network-api-impl/index.md) | [androidJvm]<br>internal class [Android29SavedNetworkApiImpl](-android29-saved-network-api-impl/index.md) : [Android29SavedNetworkApi](-android29-saved-network-api/index.md) |
| [Android29SavedNetworkDelegate](-android29-saved-network-delegate/index.md) | [androidJvm]<br>internal class [Android29SavedNetworkDelegate](-android29-saved-network-delegate/index.md)(**impl**: [Android29SavedNetworkApiImpl](-android29-saved-network-api-impl/index.md)) : [SavedNetworkApi](../com.isupatches.android.wisefy.savednetworks/-saved-network-api/index.md) |
| [Android30SavedNetworkApi](-android30-saved-network-api/index.md) | [androidJvm]<br>internal interface [Android30SavedNetworkApi](-android30-saved-network-api/index.md) |
| [Android30SavedNetworkApiImpl](-android30-saved-network-api-impl/index.md) | [androidJvm]<br>internal class [Android30SavedNetworkApiImpl](-android30-saved-network-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [Android30SavedNetworkApi](-android30-saved-network-api/index.md) |
| [Android30SavedNetworkDelegate](-android30-saved-network-delegate/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>internal class [Android30SavedNetworkDelegate](-android30-saved-network-delegate/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **impl**: [Android30SavedNetworkApiImpl](-android30-saved-network-api-impl/index.md)) : [SavedNetworkApi](../com.isupatches.android.wisefy.savednetworks/-saved-network-api/index.md) |
| [LegacySavedNetworkApi](-legacy-saved-network-api/index.md) | [androidJvm]<br>internal interface [LegacySavedNetworkApi](-legacy-saved-network-api/index.md) |
| [LegacySavedNetworkApiImpl](-legacy-saved-network-api-impl/index.md) | [androidJvm]<br>internal class [LegacySavedNetworkApiImpl](-legacy-saved-network-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [LegacySavedNetworkApi](-legacy-saved-network-api/index.md) |
| [LegacySavedNetworkDelegate](-legacy-saved-network-delegate/index.md) | [androidJvm]<br>internal class [LegacySavedNetworkDelegate](-legacy-saved-network-delegate/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **impl**: [LegacySavedNetworkApi](-legacy-saved-network-api/index.md)) : [SavedNetworkApi](../com.isupatches.android.wisefy.savednetworks/-saved-network-api/index.md) |

## Properties

| Name | Summary |
|---|---|
| [ANDROID_Q_SAVED_NETWORK_WARNING](-a-n-d-r-o-i-d_-q_-s-a-v-e-d_-n-e-t-w-o-r-k_-w-a-r-n-i-n-g.md) | [androidJvm]<br>private const val [ANDROID_Q_SAVED_NETWORK_WARNING](-a-n-d-r-o-i-d_-q_-s-a-v-e-d_-n-e-t-w-o-r-k_-w-a-r-n-i-n-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
