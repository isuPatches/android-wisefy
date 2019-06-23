-verbose

-dontobfuscate

-keep class com.isupatches.wisefysample.** { *; }
-keep public interface com.isupatches.wisefysample.** { *; }

-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

-keep class io.reactivex.schedulers.Schedulers { *; }
