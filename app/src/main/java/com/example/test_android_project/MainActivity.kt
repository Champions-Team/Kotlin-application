package com.example.test_android_project

import android.app.appsearch.SearchResult
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import android.R.attr.onClick
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.TextButton
import androidx.wear.compose.material3.TextButtonDefaults
import com.example.test_android_project.ui.theme.PurpleGrey40
import com.example.test_android_project.ui.theme.PurpleGrey80

//import com.example.test_android_project.ui.theme.Test_android_projectTheme

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
                         AppHeader(padding)
                         Spacer(modifier = Modifier.height(190.dp))
                         CheckFields()
                    }
                }
            )
        }
    }
}



@Composable
fun CheckFields(){
    var errorState by remember {mutableStateOf(false)}
    var textStateEmail by remember {mutableStateOf("")}
    var textStatePassword by remember {mutableStateOf("")}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = textStateEmail,
            onValueChange = {
                textStateEmail = it
                errorState = if (EMAIL_ADDRESS.matcher(it).matches()) false else true
            },
            shape = RoundedCornerShape(17.dp),
            placeholder = {
                Text(
                    text = "example@gmail.com",
                    color = Color.LightGray
                )
            },
            singleLine = true,
            label = {
                Text(
                    text = if (!errorState) "Your email:" else "Wrong email!"
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        textStateEmail = ""
                        errorState = false
                    }
                ){
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear button for email field"
                    )
                }
            },
            isError = errorState
        )
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedTextField(
            value = textStatePassword,
            onValueChange = {
                textStatePassword = it
            },
            shape = RoundedCornerShape(17.dp),
            placeholder = {
                Text(
                    text = "example_password",
                    color = Color.LightGray
                )
            },
            singleLine = true,
            label = {
                Text(
                    text = "Your password:"
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        textStatePassword = ""
                    }
                ){
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear button for password field"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            )
        ){
            Text(
                text = "Log in"
            )
        }
    }
}
@Composable
fun AppHeader(padding: PaddingValues){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding)
    ){
        Text(
            text = "Registration",
            fontSize = 30.sp
        )
        Text(
            text = "Enter your email & password"
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun CheckFieldsPreview(){
    CheckFields()
}
@Composable
@Preview(showBackground = true)
private fun AppHeaderPreview() {
    AppHeader(PaddingValues())
}
