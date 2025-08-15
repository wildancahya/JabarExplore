package com.unikom.jabarexplore.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unikom.jabarexplore.R
import com.unikom.jabarexplore.ui.theme.PrimaryColor
import com.unikom.jabarexplore.ui.theme.TextDark
import com.unikom.jabarexplore.ui.theme.BackgroundColor


data class WalkthroughPage(
    val image: Int,
    val title: String,
    val description: String
)

@Composable
fun WalkthroughScreen(navController: NavController) {
    val pages = listOf(
        WalkthroughPage(
            image = R.drawable.jabarexplore_splash, // ganti sesuai gambar kamu
            title = "Explore Destinations",
            description = "Temukan destinasi wisata terbaik di Jawa Barat dengan informasi lengkap."
        ),
        WalkthroughPage(
            image = R.drawable.jabarexplore_splash,
            title = "AI Travel Itinerary",
            description = "Buat rencana perjalanan otomatis sesuai preferensi kamu."
        ),
        WalkthroughPage(
            image = R.drawable.jabarexplore_splash,
            title = "AR Heritage Tour",
            description = "Jelajahi sejarah melalui tur interaktif berbasis Augmented Reality."
        )
    )

    var currentPage by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Gambar
        Image(
            painter = painterResource(id = pages[currentPage].image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        // Judul
        Text(
            text = pages[currentPage].title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColor
        )

        // Deskripsi
        Text(
            text = pages[currentPage].description,
            fontSize = 16.sp,
            color = TextDark,
            modifier = Modifier.padding(horizontal = 24.dp),
            lineHeight = 22.sp
        )

        // Indicator Dots
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            pages.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (index == currentPage) 12.dp else 8.dp)
                        .background(
                            if (index == currentPage) PrimaryColor else Color.LightGray,
                            CircleShape
                        )
                )
            }
        }

        // Tombol
        Button(
            onClick = {
                if (currentPage < pages.lastIndex) {
                    currentPage++
                } else {
                    navController.navigate("login") // pindah ke halaman home
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = if (currentPage == pages.lastIndex) "Get Started" else "Next",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}