package org.example.gems.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.gems.data.mock.MockData
import org.example.gems.navigation.LocalNavigationController
import org.example.gems.navigation.Screen
import org.example.gems.ui.components.GlassTopAppBar
import org.example.gems.ui.components.IOSCard
import org.example.gems.ui.components.IOSLargeTitle
import org.example.gems.ui.components.StudentInfoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val navController = LocalNavigationController.current
    val student = MockData.student

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            GlassTopAppBar(
                actions = {
                    IconButton(onClick = { navController.navigateTo(Screen.Profile) }) {
                        Icon(Icons.Rounded.AccountCircle, contentDescription = "Profile", tint = MaterialTheme.colorScheme.primary)
                    }
                    IconButton(onClick = { navController.navigateTo(Screen.Settings) }) {
                        Icon(Icons.Rounded.Settings, contentDescription = "Settings", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            IOSLargeTitle("Dashboard")

            // Modern Redesigned Student Info Card
            StudentInfoCard(
                student = student,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            Text(
                "Quick Access",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)
            )

            // Grid of Quick Access Cards with Rounded Icons
            val menuItems = listOf(
                DashboardMenuItem("Attendance", Icons.Rounded.EventAvailable, Screen.Attendance, Color(0xFF34C759)),
                DashboardMenuItem("Marks", Icons.Rounded.Analytics, Screen.Marks, Color(0xFFFF9500)),
                DashboardMenuItem("Timetable", Icons.Rounded.Schedule, Screen.Timetable, Color(0xFF007AFF)),
                DashboardMenuItem("Notices", Icons.Rounded.Campaign, Screen.Notices, Color(0xFFFF3B30))
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    DashboardCard(menuItems[0], Modifier.weight(1f)) { navController.navigateTo(it) }
                    DashboardCard(menuItems[1], Modifier.weight(1f)) { navController.navigateTo(it) }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    DashboardCard(menuItems[2], Modifier.weight(1f)) { navController.navigateTo(it) }
                    DashboardCard(menuItems[3], Modifier.weight(1f)) { navController.navigateTo(it) }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

data class DashboardMenuItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen,
    val color: Color
)

@Composable
fun DashboardCard(item: DashboardMenuItem, modifier: Modifier = Modifier, onNavigate: (Screen) -> Unit) {
    IOSCard(
        modifier = modifier
            .padding(8.dp)
            .height(140.dp),
        onClick = { onNavigate(item.screen) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                color = item.color.copy(alpha = 0.1f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        item.icon,
                        contentDescription = item.title,
                        tint = item.color,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                item.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
