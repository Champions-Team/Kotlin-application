package com.example.test_android_project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ListOfOrganizationsScreen(navController: NavController) {

    var searchState by remember{mutableStateOf("")}

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            BackArrowButton(navController)
        }
        Header(
            searchState = searchState,
            onSearchChange = {
                searchState = it
            },
            onClearClicked = {
                searchState = ""
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        ListOfOrganizations()
        CommonButton(
            text = "go to Sign up",
            onButtonClick = {
                navController.navigate("registration")
            },
        )
    }
}

@Composable
fun Header(
    searchState: String,
    onSearchChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "List of organizations",
            fontSize = 30.sp,
        )
        Text(
            text = "choose organization to help it",
            color = Color.Gray
        )
        Spacer(Modifier.height(25.dp))
        OutlinedTextField(
            value = searchState,
            onValueChange = {
                onSearchChange(it)
            },
            shape = RoundedCornerShape(20.dp),
            placeholder = {
                Text(
                    text = "search...",
                    color = Color.Gray
                )
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black
            ),
            leadingIcon = {
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search button icon"
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onClearClicked()
                    }
                ){
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "clear button for search field"
                    )
                }
            },
        )
    }
}

@Composable
fun ListOfOrganizations(){
    LazyColumn (
        modifier = Modifier
            .width(300.dp)
            .height(250.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.background)
    ){
        items(322){ index: Int ->
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(75.dp)
                    .fillMaxWidth()
                    .padding(15.dp, 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
                    .background(color = MaterialTheme.colorScheme.background)
            ){
                Spacer(Modifier.width(15.dp))
                Text(
                    text = "${index + 1}. ",
                    fontSize = 25.sp,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "organization's information"
                )
                Text(
                    text = "some information....",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(5.dp)
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
    Header("search", {}, {})
}

@Preview(showBackground = true)
@Composable
private fun ListOfOrganizationsPreview(){
    ListOfOrganizations()
}