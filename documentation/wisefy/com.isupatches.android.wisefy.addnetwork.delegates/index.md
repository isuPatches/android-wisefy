//[wisefy](../../index.md)/[com.isupatches.android.wisefy.addnetwork.delegates](index.md)

# Package com.isupatches.android.wisefy.addnetwork.delegates

## Types

| Name | Summary |
|---|---|
| [Android29AddNetworkApi](-android29-add-network-api/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>internal interface [Android29AddNetworkApi](-android29-add-network-api/index.md) |
| [Android29AddNetworkApiImpl](-android29-add-network-api-impl/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>internal class [Android29AddNetworkApiImpl](-android29-add-network-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)?) : [Android29AddNetworkApi](-android29-add-network-api/index.md) |
| [Android29AddNetworkDelegate](-android29-add-network-delegate/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>internal class [Android29AddNetworkDelegate](-android29-add-network-delegate/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **logger**: [WisefyLogger](../com.isupatches.android.wisefy.logging/-wisefy-logger/index.md)?, **impl**: [Android29AddNetworkApi](-android29-add-network-api/index.md)) : [AddNetworkApi](../com.isupatches.android.wisefy.addnetwork/-add-network-api/index.md) |
| [Android30AddNetworkApi](-android30-add-network-api/index.md) | [androidJvm]<br>internal interface [Android30AddNetworkApi](-android30-add-network-api/index.md) |
| [Android30AddNetworkApiImpl](-android30-add-network-api-impl/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>internal class [Android30AddNetworkApiImpl](-android30-add-network-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [Android30AddNetworkApi](-android30-add-network-api/index.md) |
| [Android30AddNetworkDelegate](-android30-add-network-delegate/index.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 30)<br>internal class [Android30AddNetworkDelegate](-android30-add-network-delegate/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **impl**: [Android30AddNetworkApi](-android30-add-network-api/index.md)) : [AddNetworkApi](../com.isupatches.android.wisefy.addnetwork/-add-network-api/index.md) |
| [LegacyAddNetworkApi](-legacy-add-network-api/index.md) | [androidJvm]<br>internal interface [LegacyAddNetworkApi](-legacy-add-network-api/index.md) |
| [LegacyAddNetworkApiImpl](-legacy-add-network-api-impl/index.md) | [androidJvm]<br>internal class [LegacyAddNetworkApiImpl](-legacy-add-network-api-impl/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html)) : [LegacyAddNetworkApi](-legacy-add-network-api/index.md) |
| [LegacyAddNetworkDelegate](-legacy-add-network-delegate/index.md) | [androidJvm]<br>internal class [LegacyAddNetworkDelegate](-legacy-add-network-delegate/index.md)(**wifiManager**: [WifiManager](https://developer.android.com/reference/kotlin/android/net/wifi/WifiManager.html), **impl**: [LegacyAddNetworkApi](-legacy-add-network-api/index.md)) : [AddNetworkApi](../com.isupatches.android.wisefy.addnetwork/-add-network-api/index.md) |

## Properties

| Name | Summary |
|---|---|
| [ANDROID_Q_SAVE_NETWORK_WARNING](-a-n-d-r-o-i-d_-q_-s-a-v-e_-n-e-t-w-o-r-k_-w-a-r-n-i-n-g.md) | [androidJvm]<br>private const val [ANDROID_Q_SAVE_NETWORK_WARNING](-a-n-d-r-o-i-d_-q_-s-a-v-e_-n-e-t-w-o-r-k_-w-a-r-n-i-n-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [LOG_TAG](-l-o-g_-t-a-g.md) | [androidJvm]<br>private const val [LOG_TAG](-l-o-g_-t-a-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
