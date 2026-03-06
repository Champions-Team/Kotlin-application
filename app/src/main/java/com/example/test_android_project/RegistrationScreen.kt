package com.example.test_android_project

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegistrationScreen(
    navController: NavController
){

    var usersEmail by remember{mutableStateOf("")}
    var isEmailFieldValid by remember{mutableStateOf(true)}
    var usersPassword by remember{mutableStateOf("")}
    var validationMessage by remember{mutableStateOf("")}

    Column {
        AppHeader()
        Spacer(modifier = Modifier.height(30.dp))
        EmailField(
            email = usersEmail,
            isEmailValid = isEmailFieldValid,
            onEmailChange = {
                usersEmail = it
                isEmailFieldValid = EMAIL_ADDRESS.matcher(it).matches()
            },
            onClearClicked = {
                usersEmail = ""
                isEmailFieldValid = true
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        PasswordField(
            password = usersPassword,
            onPasswordChange = {
                usersPassword = it
            },
            onClearClicked = {
                usersPassword = ""
            }
        )
        CommonButton(
            text = "Sign up",
            onButtonClick = {
                validationMessage = if (!isEmailFieldValid || usersEmail.isEmpty()){
                    "Incorrect email"
                }else if (usersPassword.isEmpty()){
                    "Incorrect password"
                } else if (usersEmail == "smalanin777@gmail.com"){
                    "Such email already exists"
                }else{
                    "Successful registration!"
                }
                if (validationMessage == "Successful registration!"){
                    navController.navigate(Screen.Detail.route)
                }
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            Text(
                text = validationMessage,
                fontSize = 20.sp,
                color = Color.Black,
            )
        }
    }
}
@Composable
fun EmailField(
    email: String,
    isEmailValid: Boolean,
    onEmailChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Text(
        text = "Email",
        fontSize = 15.sp,
        modifier = Modifier
            .padding(30.dp, 0.dp)
    )
    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text ="example@gmail.com",
                color = Color.Gray
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onClearClicked()
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear button for email field"
                )
            }
        },
        isError = !isEmailValid,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
    )
}

@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Text(
        text = "Password",
        fontSize = 15.sp,
        modifier = Modifier
            .padding(30.dp, 0.dp)
    )
    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text = "example_password",
                color = Color.Gray
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onClearClicked()
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear icon for password field"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
    )
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
fun AppHeader(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp)
    ){
        Text(
            text = "Registration",
            fontSize = 30.sp,
        )
        Text(
            text = "Enter your email & password",
            color = Color.Gray
        )
    }
}
@Composable
@Preview(showBackground = true)
private fun RegistrationScreenPreview(){
    RegistrationScreen(rememberNavController())
}
@Composable
@Preview(showBackground = true)
private fun CommonButtonPreview(){
    CommonButton("Sign up", {})
}
@Composable
@Preview(showBackground = true)
private fun EmailFieldPreview(){
    EmailField("", true, {}, {})
}
@Composable
@Preview(showBackground = true)
private fun PasswordFieldsPreview(){
    PasswordField("", {}, {})
}
@Composable
@Preview(showBackground = true)
private fun AppHeaderPreview() {
    AppHeader()
}
