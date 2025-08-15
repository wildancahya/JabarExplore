package com.unikom.jabarexplore.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unikom.jabarexplore.screen.BottomNavigationBar
import com.unikom.jabarexplore.screen.DestinationsScreen
import com.unikom.jabarexplore.screen.HomeScreen
import com.unikom.jabarexplore.screen.ItineraryScreen
import com.unikom.jabarexplore.screen.LoginScreen
import com.unikom.jabarexplore.screen.ProfileScreen
import com.unikom.jabarexplore.screen.SplashScreen
import com.unikom.jabarexplore.screen.WalkthroughScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("walkthrough") {
            WalkthroughScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("main") {
            MainScreen(rootNavController = navController) // ini berisi bottom navigation dan navhost untuk home, dst.
        }
    }
}

@Composable
fun MainScreen(rootNavController: NavHostController) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("destinations") { DestinationsScreen() }
            composable("itinerary") { ItineraryScreen() }
            composable("profile") { ProfileScreen(onLogout = {
                rootNavController.navigate("login") {
                    popUpTo("main") { inclusive = true }
                }
            }) }
        }
    }
}