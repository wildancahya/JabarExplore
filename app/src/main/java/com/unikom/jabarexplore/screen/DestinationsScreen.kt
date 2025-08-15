package com.unikom.jabarexplore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unikom.jabarexplore.R
import com.unikom.jabarexplore.components.OpenStreetMapView
import com.unikom.jabarexplore.ui.theme.PrimaryColor
import androidx.compose.foundation.lazy.items

@Composable
fun DestinationsScreen() {
    val filters = listOf("Nature", "Culture", "Culinary", "Creative", "Religi")
    var selectedFilter by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(5.dp)
    ) {
        // Header (fixed)
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
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title + Filter Icon (fixed)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Explore Destinations",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* aksi filter */ }) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Filter Chips (fixed)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filters) { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = if (selectedFilter == filter) null else filter },
                    label = { Text(filter) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = PrimaryColor,
                        selectedLabelColor = Color.White,
                        containerColor = Color(0xFFF0F0F0),
                        labelColor = Color.Black
                    ),
                    leadingIcon = if (selectedFilter == filter) {
                        { Icon(Icons.Default.Check, contentDescription = null, tint = Color.White) }
                    } else null
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Map (fixed)
        OpenStreetMapView(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Scroll hanya di bagian Destination Card
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // <-- membuat hanya area ini yang scroll
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(5) { // contoh 10 destinasi
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(
                        text = "Tangkuban Perahu",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Bandung, West Java", color = Color.Gray, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(text = "4.5", fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.tangkuban_perahu),
                        contentDescription = "Tangkuban Perahu",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

