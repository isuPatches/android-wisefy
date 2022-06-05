//[security](../../../index.md)/[com.isupatches.android.wisefy.security.entities](../index.md)/[ContainsSecurityCapabilityRequest](index.md)

# ContainsSecurityCapabilityRequest

[androidJvm]\
data class [ContainsSecurityCapabilityRequest](index.md)(val network: [AccessPointData](../../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md), val securityCapability: [SecurityCapability](../-security-capability/index.md))

A data class that is used to represent requests to check if a network contains a given security capability.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| [com.isupatches.android.wisefy.accesspoints.entities.AccessPointData](../../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md) |  |
| [com.isupatches.android.wisefy.security.entities.SecurityCapability](../-security-capability/index.md) |  |

## Constructors

| | |
|---|---|
| [ContainsSecurityCapabilityRequest](-contains-security-capability-request.md) | [androidJvm]<br>fun [ContainsSecurityCapabilityRequest](-contains-security-capability-request.md)(network: [AccessPointData](../../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md), securityCapability: [SecurityCapability](../-security-capability/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [network](network.md) | [androidJvm]<br>val [network](network.md): [AccessPointData](../../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md)<br>The network to check the capabilities of |
| [securityCapability](security-capability.md) | [androidJvm]<br>val [securityCapability](security-capability.md): [SecurityCapability](../-security-capability/index.md)<br>The security capability to check for |
