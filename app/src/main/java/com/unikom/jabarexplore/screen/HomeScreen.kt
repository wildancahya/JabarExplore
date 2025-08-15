package com.unikom.jabarexplore.screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unikom.jabarexplore.R
import com.unikom.jabarexplore.ui.theme.PrimaryColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiNature
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun HomeScreen() {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryColor)
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "JabarExplore",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar (Material3 style)
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search destinations or events") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = MaterialTheme.shapes.large
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Featured Destination Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.papandayan),
                    contentDescription = "Gunung Papandayan",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(Color(0x80000000))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Gunung Papandayan",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Gunung di Jawa Barat berada di Garut",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Travel Itinerary Section
        Text(
            text = "AI Travel Itinerary",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Grid Menu
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeMenuCard("Destinations", Icons.Default.Explore, Modifier.weight(1f))
                HomeMenuCard("Itinerary AI", Icons.Default.EmojiNature, Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                HomeMenuCard("Tickets & Vouchers", Icons.Default.ConfirmationNumber, Modifier.weight(1f))
                HomeMenuCard("Culinary & Tourism", Icons.Default.Fastfood, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun HomeMenuCard(title: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = PrimaryColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 14.sp)
        }
    }
}
