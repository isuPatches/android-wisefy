-verbose

-dontobfuscate

-keep class com.isupatches.android.wisefy.** { *; }

# Java lang for AGP
-dontwarn java.lang.instrument.ClassFileTransformer
