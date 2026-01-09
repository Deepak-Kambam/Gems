# Compose specific rules
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Keep Compose internal annotations
-keep @interface androidx.compose.runtime.Composable
-keep @interface androidx.compose.runtime.ReadOnlyComposable

# Standard KMP keep rules
-keep class org.example.gems.** { *; }
