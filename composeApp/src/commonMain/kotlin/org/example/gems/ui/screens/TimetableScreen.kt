package org.example.gems.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.gems.data.mock.MockData
import org.example.gems.navigation.LocalNavigationController
import org.example.gems.ui.components.GlassTopAppBar
import org.example.gems.ui.components.IOSCard
import org.example.gems.ui.components.IOSLargeTitle
import org.example.gems.ui.theme.iOSBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen() {
    val navController = LocalNavigationController.current
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    var selectedDay by remember { mutableStateOf("Mon") }
    val timetable = MockData.weeklyTimetable[selectedDay] ?: emptyList()

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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            IOSLargeTitle("Timetable")

            // Day Selector (Apple-Style Segmented Picker)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(days) { day ->
                    val isSelected = selectedDay == day
                    val backgroundColor by animateColorAsState(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                    val contentColor by animateColorAsState(if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface)
                    val scale by animateFloatAsState(
                        targetValue = if (isSelected) 1.15f else 1f,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
                    )
                    val elevation by animateDpAsState(if (isSelected) 8.dp else 0.dp)

                    Surface(
                        onClick = { selectedDay = day },
                        shape = RoundedCornerShape(16.dp),
                        color = backgroundColor,
                        contentColor = contentColor,
                        shadowElevation = elevation,
                        modifier = Modifier
                            .width(64.dp)
                            .height(48.dp)
                            .scale(scale)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                day,
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = if (isSelected) 15.sp else 14.sp
                                )
                            )
                        }
                    }
                }
            }

            // Animated Timetable List
            AnimatedContent(
                targetState = timetable,
                transitionSpec = {
                    fadeIn(animationSpec = tween(300)) + slideInVertically(initialOffsetY = { 20 }) togetherWith
                            fadeOut(animationSpec = tween(200))
                },
                modifier = Modifier.weight(1f)
            ) { currentTimetable ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(currentTimetable) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.width(60.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "P${item.period}",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Box(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .height(60.dp)
                                        .background(MaterialTheme.colorScheme.outlineVariant)
                                )
                            }

                            IOSCard(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 10.dp)
                            ) {
                                Column {
                                    Text(
                                        item.subject,
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        item.faculty,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        item.timeSlot,
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                        color = iOSBlue
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
