package com.example.test_android_project

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import okhttp3.Request
import okhttp3.FormBody

@Composable
fun RegistrationScreen(
    navController: NavController
){

    var usersEmail by remember{mutableStateOf("")}
    var isEmailFieldValid by remember{mutableStateOf(true)}
    var usersPassword by remember{mutableStateOf("")}
    var validationMessage: String? by remember{mutableStateOf("")}
    var userName by remember{mutableStateOf("")}


    Column {
        BackArrowButton(navController)
        AppHeader()
        Spacer(modifier = Modifier.height(30.dp))
        NameField(
            username = userName,
            onUsernameChange = {
                userName = it
            },
            onClearClicked = {
                userName = ""
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
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
                validationMessage = if (!isEmailFieldValid || usersEmail.isEmpty() || userName.isEmpty()){
                    "Incorrect email or password!"
                }
                else{
                    "Correct"
                }
                if (validationMessage == "Correct") {

                    val formBody = FormBody.Builder()
                        .add("name",userName)
                        .add("email",usersEmail)
                        .add("password", usersPassword)
                        .build()

                    val request = Request.Builder()
                        .url("url")  // <-- URL
                        .post(formBody)
                        .build()

                    val response = client.newCall(request).execute()
                    validationMessage = response.body?.string()
                    navController.navigate("listOfOrganizations")
                }
            }
        )
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
fun NameField(
    username: String,
    onUsernameChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Text(
        text = "Your name",
        fontSize = 15.sp,
        modifier = Modifier
            .padding(30.dp, 0.dp)
    )
    OutlinedTextField(
        value = username,
        onValueChange = {
            onUsernameChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text = "Vsevolod_Kuznetsov",
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
                    contentDescription = "clear icon for name field"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp)
    )
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
