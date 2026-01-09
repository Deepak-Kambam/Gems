package org.example.gems.navigation

import androidx.compose.runtime.Composable

@Composable
actual fun NavBackHandler(enabled: Boolean, onBack: () -> Unit) {
    // iOS doesn't have a hardware back button. 
    // Back navigation is usually handled via UI (gestures/buttons).
}
