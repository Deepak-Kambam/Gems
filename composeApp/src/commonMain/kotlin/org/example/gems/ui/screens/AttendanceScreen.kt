package org.example.gems.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.gems.data.mock.MockData
import org.example.gems.navigation.LocalNavigationController
import org.example.gems.ui.components.GlassTopAppBar
import org.example.gems.ui.components.IOSCard
import org.example.gems.ui.components.IOSLargeTitle
import org.example.gems.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen() {
    val navController = LocalNavigationController.current
    val attendance = MockData.attendanceList

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            GlassTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.goBack() }) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                IOSLargeTitle("Attendance")
            }

            items(attendance) { item ->
                IOSCard(
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                item.subject,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                "${item.percentage}%",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = when {
                                        item.percentage >= 85 -> iOSGreen
                                        item.percentage >= 75 -> iOSOrange
                                        else -> iOSRed
                                    }
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LinearProgressIndicator(
                            progress = { (item.percentage / 100).toFloat() },
                            modifier = Modifier.fillMaxWidth().height(10.dp),
                            color = when {
                                item.percentage >= 85 -> iOSGreen
                                item.percentage >= 75 -> iOSOrange
                                else -> iOSRed
                            },
                            trackColor = if (isSystemInDarkTheme()) Color(0xFF2C2C2E) else Color(0xFFE5E5EA),
                            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text("Classes Attended", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("${item.classesAttended}", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Total Classes", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("${item.classesConducted}", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold))
                            }
                        }
                    }
                }
            }
        }
    }
}
