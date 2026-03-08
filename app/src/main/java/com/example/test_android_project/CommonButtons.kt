package com.example.test_android_project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BackArrowButton(navController: NavController){
    IconButton(
        onClick = {
            navController.popBackStack()
        },
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(50.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(50.dp))
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
fun CommonButton(
    text: String,
    onButtonClick: (String) -> Unit
){
    Button(
        shape = RoundedCornerShape(17.dp),
        onClick = {
            onButtonClick(text)
        },
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 15.dp)
    ){
        Text(
            text = text,
        )
    }
}
@Composable
@Preview(showBackground = true)
private fun BackArrowButtonPreview(){
    BackArrowButton(rememberNavController())
}
@Composable
@Preview(showBackground = true)
private fun CommonButtonPreview(){
    CommonButton("click", {})
}