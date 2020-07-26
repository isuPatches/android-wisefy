-dontnote

-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

-keepattributes *Annotation*, EnclosingMethod, InnerClasses, Signature

# Kotlin
-keep class kotlin.collections.CollectionsKt { *; }
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

# RxJava
-keep class io.reactivex.schedulers.Schedulers {
    public static <methods>;
}

# Java lang for AGP
-keep class java.lang.instrument.ClassFileTransformer { *; }
-dontwarn java.lang.instrument.ClassFileTransformer
