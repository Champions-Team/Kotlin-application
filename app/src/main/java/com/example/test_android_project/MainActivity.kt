package com.example.test_android_project

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import okhttp3.OkHttpClient

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome",
    ){
        composable("listOfOrganizations"){ ListOfOrganizationsScreen(navController)}
        composable("registration"){ RegistrationScreen(navController)}
        composable("authorization"){ AuthorizationScreen(navController)}
        composable("welcome"){ WelcomeScreen(navController)}
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
                 modifier = Modifier
                     .fillMaxSize()
            ){
                AppNavigation()
            }
        }
    }
}

