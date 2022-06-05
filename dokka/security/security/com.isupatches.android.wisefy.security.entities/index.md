//[security](../../index.md)/[com.isupatches.android.wisefy.security.entities](index.md)

# Package com.isupatches.android.wisefy.security.entities

## Types

| Name | Summary |
|---|---|
| [ContainsSecurityCapabilityRequest](-contains-security-capability-request/index.md) | [androidJvm]<br>data class [ContainsSecurityCapabilityRequest](-contains-security-capability-request/index.md)(val network: [AccessPointData](../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md), val securityCapability: [SecurityCapability](-security-capability/index.md))<br>A data class that is used to represent requests to check if a network contains a given security capability. |
| [ContainsSecurityCapabilityResult](-contains-security-capability-result/index.md) | [androidJvm]<br>sealed class [ContainsSecurityCapabilityResult](-contains-security-capability-result/index.md)<br>A set of classes and objects that are used to represent a result while checking if a network contains a given security capability. |
| [IsNetworkSecureRequest](-is-network-secure-request/index.md) | [androidJvm]<br>data class [IsNetworkSecureRequest](-is-network-secure-request/index.md)(val network: [AccessPointData](../../../accesspoints/accesspoints/com.isupatches.android.wisefy.accesspoints.entities/-access-point-data/index.md))<br>A data class that is used to represent requests to check if a network is secure. |
| [IsNetworkSecureResult](-is-network-secure-result/index.md) | [androidJvm]<br>sealed class [IsNetworkSecureResult](-is-network-secure-result/index.md)<br>A set of classes and objects that are used to represent a result while checking if a network is secure. |
| [SecurityCapability](-security-capability/index.md) | [androidJvm]<br>enum [SecurityCapability](-security-capability/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SecurityCapability](-security-capability/index.md)&gt; <br>A set of supported security capabilities. |
