package com.example.test_android_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.PaddingValues
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
        startDestination = "listOfOrganizations",
    ){
        composable("listOfOrganizations"){ ListOfOrganizationsScreen(navController)}
        composable("registration"){ RegistrationScreen(navController)}
    }
}

val client = OkHttpClient()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                content = { padding: PaddingValues ->
                    Column (
                         horizontalAlignment = Alignment.CenterHorizontally,
                         modifier = Modifier
                             .padding(padding)
                             .fillMaxSize()
                    ){
                        AppNavigation()
                    }
                }
            )
        }
    }
}

