package com.example.test_android_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test_android_project.ui.theme.GraySystemColor
import com.example.test_android_project.ui.theme.PinkSystemColor

@Composable
fun ListOfOrganizationsScreen(
    navController: NavController
){

    var searchState by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.background_image),
            contentDescription = null,
            modifier = Modifier.offset(y = -250.dp, x = 0.dp)
        )
        UserProfileButton()
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(0.dp, 200.dp, 0.dp, 0.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(25.dp, 0.dp, 25.dp, 0.dp)
            ) {
                Text(
                    text = "Организации",
                    fontSize = 38.sp,
                    fontFamily = FontFamily(Font(R.font.system_font))
                )
                Spacer(Modifier.height(20.dp))
                SearchField(
                    searchState = searchState,
                    onSearchChange = {
                        searchState = it
                    },
                    onClearClicked = {
                        searchState = ""
                    }
                )
                Spacer(Modifier.height(20.dp))
            }
            VerticalListOfOrganizations()
        }
    }
}

@Composable
fun VerticalListOfOrganizations(){
    LazyColumn(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
    ){
        items(50){
            HorizontalCardOfOrganization()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListOfOrganizationsScreenPrevew(){
    ListOfOrganizationsScreen(rememberNavController())
}