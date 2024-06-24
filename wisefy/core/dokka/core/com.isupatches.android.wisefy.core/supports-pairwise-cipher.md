//[core](../../index.md)/[com.isupatches.android.wisefy.core](index.md)/[supportsPairwiseCipher](supports-pairwise-cipher.md)

# supportsPairwiseCipher

[androidJvm]\
fun [ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html).[supportsPairwiseCipher](supports-pairwise-cipher.md)(pairwiseCipher: [PairwiseCipher](../com.isupatches.android.wisefy.core.entities/-pairwise-cipher/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

An extension function to check if the given access point supports a certain [PairwiseCipher](../com.isupatches.android.wisefy.core.entities/-pairwise-cipher/index.md) based on its rawValue.

#### Receiver

[ScanResult](https://developer.android.com/reference/kotlin/android/net/wifi/ScanResult.html)

#### Return

Boolean - True if the access point supports the [PairwiseCipher](../com.isupatches.android.wisefy.core.entities/-pairwise-cipher/index.md), otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| pairwiseCipher | The given [PairwiseCipher](../com.isupatches.android.wisefy.core.entities/-pairwise-cipher/index.md) to check the access point for |

#### See also

| |
|---|
| [PairwiseCipher](../com.isupatches.android.wisefy.core.entities/-pairwise-cipher/index.md) |
