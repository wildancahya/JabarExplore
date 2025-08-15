package com.unikom.jabarexplore.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.unikom.jabarexplore.R
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val alpha = remember { Animatable(0f) }
    val auth = FirebaseAuth.getInstance()

    // Animasi fade-in
    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
        delay(2000) // tunggu sebentar sebelum pindah
        if (auth.currentUser != null) {
            // User sudah login → langsung ke main
            navController.navigate("main") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            // Belum login → ke onboarding / auth
            navController.navigate("walkthrough") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.jabarexplore_splash),
                contentDescription = "Logo KerjaBDG",
                modifier = Modifier
                    .size(300.dp)
                    .graphicsLayer { this.alpha = alpha.value }
            )
        }
    }
}