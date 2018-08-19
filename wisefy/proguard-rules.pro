-verbose

-dontobfuscate
#-keepattributes SourceFile, LineNumberTable # For stacktraces
#-keepattributes LocalVariableTable, LocalVariableTypeTable # For debugging
#-keepattributes MethodParameters

-keep class com.isupatches.wisefy.** { *; }
-keep public interface com.isupatches.wisefy.** { *; }