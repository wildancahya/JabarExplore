package com.unikom.jabarexplore.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.unikom.jabarexplore.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit // callback ke login screen
) {
    val context = LocalContext.current
    val account = GoogleSignIn.getLastSignedInAccount(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Foto profil
            Image(
                painter = rememberAsyncImagePainter(account?.photoUrl),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nama & Email
            Text(
                text = account?.displayName ?: "Guest User",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = account?.email ?: "-",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Tombol logout
            Button(
                onClick = {
                    signOutFromGoogle(context) {
                        onLogout()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text("Logout", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

// Fungsi logout dari Google
fun signOutFromGoogle(context: Context, onLoggedOut: () -> Unit) {
    val gso = com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder(
        com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
    ).build()
    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)
    googleSignInClient.signOut().addOnCompleteListener {
        onLoggedOut()
    }
}
