package com.example.test_android_project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.wear.compose.material3.OpenOnPhoneDialogDefaults.Icon
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.OpenOnPhoneDialogDefaults.Icon

@Composable
fun ListOfOrganizationsScreen(navController: NavController) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ){

        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            BackArrowButton(navController)
        }
        Header()
        Spacer(modifier = Modifier.height(20.dp))
        ListOfOrganizations()
    }
}

@Composable
fun Header(){
    Text(
        text = "List of organizations",
        fontSize = 30.sp,
    )
}
@Composable
fun BackArrowButton(navController: NavController){
    IconButton(
        onClick = {
            navController.popBackStack()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "back button on list screen",
            modifier = Modifier
                .height(65.dp)
        )
    }
}
@Composable
fun ListOfOrganizations(){
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
            .background(color = Color.Black)
    ){
        items(322){ index: Int ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
            ){
                Text(
                    text = "${index + 1}. ",
                    fontSize = 25.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(30.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListOfOrganizationsScreenPreview(){
    ListOfOrganizationsScreen(rememberNavController())
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview(){
    Header()
}

@Preview(showBackground = true)
@Composable
private fun ListOfOrganizationsPreview(){
    ListOfOrganizations()
}