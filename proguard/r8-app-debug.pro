-include r8-common.pro
-include r8-app-common.pro

-verbose
-dontobfuscate

-keepattributes SourceFile, LineNumberTable

-keep class com.isupatches.android.wisefy.sample.DebugMainApplication { *; }
