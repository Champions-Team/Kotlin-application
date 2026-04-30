package com.example.test_android_project

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import okhttp3.OkHttpClient

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }

    NavHost(
        navController = navController,
        startDestination = "welcome",
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        modifier = Modifier.fillMaxSize()
    ){
        composable("listOfOrganizations"){ ListOfOrganizationsScreen(navController) }
        composable("registration"){ RegistrationScreen(navController, dataStoreManager) }
        composable("authorization"){ AuthorizationScreen(navController, dataStoreManager) }
        composable("welcome"){ WelcomeScreen(navController) }
        composable("main"){ MainScreen(navController) }
    }
}

val client = OkHttpClient()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        enableEdgeToEdge()
        setContent {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                AppNavigation()
            }
        }
    }
}