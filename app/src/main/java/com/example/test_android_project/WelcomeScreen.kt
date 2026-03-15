package com.example.test_android_project

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun WelcomeScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background_image),
            contentDescription = null,
        )
    }
    Column (
        modifier = Modifier.padding(24.dp, 0.dp)
    ){
        Spacer(Modifier.height(520.dp))
        Column(
            modifier = Modifier
                .height(100.dp)
                .width(309.dp)
        ) {
            Text(
                text = "Добрый день",
                fontFamily = FontFamily(Font(R.font.system_font)),
                fontSize = 40.sp,
                modifier = Modifier
                    .height(44.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Нажмите продолжить для регистрации",
                fontFamily = FontFamily(Font(R.font.system_font)),
                color = Color.Gray,
                modifier = Modifier
                    .height(40.dp)
            )
        }
        Spacer(Modifier.height(85.dp))
        Row {
            Spacer(Modifier.width(135.dp))
            ContinueButton(
                navController
            )
        }
    }
}


@Composable
fun ContinueButton(
    navController: NavController
){
    Button(
        onClick = { navController.navigate("registration") },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Продолжить",
            fontFamily = FontFamily(Font(R.font.system_font)),
            color = Color.Gray
            )
        Spacer(modifier = Modifier.width(16.dp)) // space between text and icon
        Icon(
            painter = painterResource(R.drawable.continue_button),
            contentDescription = "continue",
            tint = Color.Unspecified
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview(){
    WelcomeScreen(rememberNavController())
}