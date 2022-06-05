//[security](../../../index.md)/[com.isupatches.android.wisefy.security.entities](../index.md)/[ContainsSecurityCapabilityRequest](index.md)

# ContainsSecurityCapabilityRequest

[androidJvm]\
data class [ContainsSecurityCapabilityRequest](index.md)(val network: AccessPointData, val securityCapability: [SecurityCapability](../-security-capability/index.md))

A data class that is used to represent requests to check if a network contains a given security capability.

#### Author

Patches Klinefelter

#### Since

03/2022

## See also

androidJvm

| | |
|---|---|
| com.isupatches.android.wisefy.accesspoints.entities.AccessPointData |  |
| [com.isupatches.android.wisefy.security.entities.SecurityCapability](../-security-capability/index.md) |  |

## Constructors

| | |
|---|---|
| [ContainsSecurityCapabilityRequest](-contains-security-capability-request.md) | [androidJvm]<br>fun [ContainsSecurityCapabilityRequest](-contains-security-capability-request.md)(network: AccessPointData, securityCapability: [SecurityCapability](../-security-capability/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): AccessPointData |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [SecurityCapability](../-security-capability/index.md) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(network: AccessPointData, securityCapability: [SecurityCapability](../-security-capability/index.md)): [ContainsSecurityCapabilityRequest](index.md) |
| [equals](../-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730) | [androidJvm]<br>open operator override fun [equals](../-security-capability/-companion/index.md#585090901%2FFunctions%2F1459372730)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730) | [androidJvm]<br>open override fun [hashCode](../-security-capability/-companion/index.md#1794629105%2FFunctions%2F1459372730)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730) | [androidJvm]<br>open override fun [toString](../-security-capability/-companion/index.md#1616463040%2FFunctions%2F1459372730)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [network](network.md) | [androidJvm]<br>val [network](network.md): AccessPointData<br>The network to check the capabilities of |
| [securityCapability](security-capability.md) | [androidJvm]<br>val [securityCapability](security-capability.md): [SecurityCapability](../-security-capability/index.md)<br>The security capability to check for |
