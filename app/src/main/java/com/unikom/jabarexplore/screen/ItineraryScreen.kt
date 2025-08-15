package com.unikom.jabarexplore.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unikom.jabarexplore.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItineraryScreen(
    onBackClick: () -> Unit = {}
) {
    var travelDuration by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }
    var tripType by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val tripTypes = listOf("Nature", "Culture", "Culinary", "Adventure")

    val itineraries = listOf(
        "Day 1" to listOf("Mount Tangkuban Perahu", "Kota Tua"),
        "Day 2" to listOf("Situ Patenggang", "Kawah Putih")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("AI Itinerary", fontWeight = FontWeight.Bold, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryColor
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("AI Travel Itinerary", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            item {
                OutlinedTextField(
                    value = travelDuration,
                    onValueChange = { travelDuration = it },
                    label = { Text("Travel Duration") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            item {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = tripType,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Type of Trip") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        tripTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    tripType = type
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = budget,
                    onValueChange = { budget = it },
                    label = { Text("Budget") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }

            item {
                Button(
                    onClick = { /* TODO: Create plan */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("Create Plan", color = Color.White, fontSize = 16.sp)
                }
            }

            item {
                Text("Itineraries", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            items(itineraries) { (day, places) ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(day, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(Modifier.height(4.dp))
                        places.forEach {
                            Text("• $it", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
