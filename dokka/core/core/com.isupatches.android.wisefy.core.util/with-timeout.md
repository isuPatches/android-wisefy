//[core](../../index.md)/[com.isupatches.android.wisefy.core.util](index.md)/[withTimeout](with-timeout.md)

# withTimeout

[androidJvm]\
fun [withTimeout](with-timeout.md)(timeoutInMillis: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), block: () -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

A convenience function that will wait for a given time until the provided function returns true.

Usage example: withTimeout(timeoutInMillis) {     filteredAccessPoints = filterAccessPoints(filterDuplicates, matcher)     // Stops loop at end of timeout or if we have a non-empty list of access points     filteredAccessPoints.isNotEmpty() }

#### Return

Boolean - True if the condition was met before the timeout was reached, otherwise false

#### Author

Patches Barrett

#### Since

12/2022, version 5.0.0

#### Parameters

androidJvm

| | |
|---|---|
| timeoutInMillis | The allotted time for the code to execute before aborting |
| block | A code block that should return true to exit the loop, otherwise false to continue until the time limit is met |
