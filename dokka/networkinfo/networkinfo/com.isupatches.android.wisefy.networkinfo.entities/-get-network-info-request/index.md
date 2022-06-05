//[networkinfo](../../../index.md)/[com.isupatches.android.wisefy.networkinfo.entities](../index.md)/[GetNetworkInfoRequest](index.md)

# GetNetworkInfoRequest

[androidJvm]\
data class [GetNetworkInfoRequest](index.md)(val network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null)

A data representation of a request to get information for a network.

*NOTES*

- 
   Assumes current network if a network is not passed in

#### Author

Patches Klinefelter

#### Since

03/2022

## Constructors

| | |
|---|---|
| [GetNetworkInfoRequest](-get-network-info-request.md) | [androidJvm]<br>fun [GetNetworkInfoRequest](-get-network-info-request.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [network](network.md) | [androidJvm]<br>val [network](network.md): [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)? = null<br>The optional network to use while getting network information |
