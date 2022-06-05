//[core](../../index.md)/[com.isupatches.android.wisefy.core.util](index.md)

# Package com.isupatches.android.wisefy.core.util

## Types

| Name | Summary |
|---|---|
| [SdkUtil](-sdk-util/index.md) | [androidJvm]<br>interface [SdkUtil](-sdk-util/index.md)<br>An interface that helps the library determine the SDK level of the device. |
| [SdkUtilImpl](-sdk-util-impl/index.md) | [androidJvm]<br>class [SdkUtilImpl](-sdk-util-impl/index.md) : [SdkUtil](-sdk-util/index.md)<br>A default implementation of [SdkUtil](-sdk-util/index.md) that helps the library determine the SDK level of the device. |

## Functions

| Name | Summary |
|---|---|
| [getNetwork](get-network.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 29)<br>@[RequiresPermission](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresPermission.html)(value = &quot;android.permission.ACCESS_NETWORK_STATE&quot;)<br>fun [ConnectivityManager](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.html).[getNetwork](get-network.md)(): [WifiInfo](https://developer.android.com/reference/kotlin/android/net/wifi/WifiInfo.html)?<br>A function that will retrieve a network from ConnectivityManager. |
| [rest](rest.md) | [androidJvm]<br>fun [rest](rest.md)()<br>An function that will sleep the calling thread for 1 second at a time. |
