package org.example.gems.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.gems.data.mock.MockData
import org.example.gems.navigation.LocalNavigationController
import org.example.gems.ui.components.GlassTopAppBar
import org.example.gems.ui.components.IOSCard
import org.example.gems.ui.components.IOSLargeTitle
import org.example.gems.ui.theme.GEMSPrimary
import org.example.gems.ui.theme.GEMSSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val navController = LocalNavigationController.current
    val student = MockData.student

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            GlassTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.goBack() }) {
                        Icon(
                            Icons.Rounded.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IOSLargeTitle("My Profile")

            Spacer(modifier = Modifier.height(32.dp))

            // Premium Profile Image - Shared Element feel with Dashboard icon
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .background(
                        brush = Brush.linearGradient(listOf(GEMSPrimary, GEMSSecondary)),
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .background(MaterialTheme.colorScheme.background, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier.size(115.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            student.name.take(1),
                            style = MaterialTheme.typography.displayLarge.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 52.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                student.name,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "Student ID: ${student.usn}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Academic Information Section with increased spacing
            ProfileSectionHeader(Icons.Rounded.School, "Academic Details")
            IOSCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ProfileItemRow(Icons.Rounded.AccountTree, "Branch", student.branch)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.Book, "Course", student.course)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.Numbers, "Semester", "${student.semester}th Semester")
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.Groups, "Section", "Section ${student.section}")
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Personal Information Section
            ProfileSectionHeader(Icons.Rounded.Person, "Personal Information")
            IOSCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ProfileItemRow(Icons.Rounded.Email, "Email Address", student.email)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.Phone, "Phone Number", student.phone)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Family Details Section
            ProfileSectionHeader(Icons.Rounded.FamilyRestroom, "Family Details")
            IOSCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ProfileItemRow(Icons.Rounded.Male, "Father's Name", student.fatherName)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.PhoneInTalk, "Father's Phone", student.fatherPhone)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.Female, "Mother's Name", student.motherName)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.PhoneInTalk, "Mother's Phone", student.motherPhone)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Address Details Section
            ProfileSectionHeader(Icons.Rounded.HomeWork, "Address Details")
            IOSCard(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp).fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    ProfileItemRow(Icons.Rounded.LocalPostOffice, "Communication Address", student.communicationAddress)
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    ProfileItemRow(Icons.Rounded.LocationOn, "Permanent Address", student.permanentAddress)
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun ProfileSectionHeader(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ProfileItemRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.width(18.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                value,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
