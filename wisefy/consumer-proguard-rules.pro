-keepattributes SourceFile, LineNumberTable
-keepattributes **
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

-dontobfuscate
-dontoptimize

-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-useuniqueclassmembernames

-verbose

-keep class com.isupatches.wisefy.** { *; }

# For lambdas
-dontwarn java.lang.invoke.**
-keep class java.lang.invoke.LambdaMetaFactory { *; }