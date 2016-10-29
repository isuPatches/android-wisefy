-keepattributes SourceFile, LineNumberTable
-keepattributes **
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

-dontobfuscate
-dontpreverify
-dontoptimize

-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-useuniqueclassmembernames

-verbose

-keep class com.isupatches.** { *; }