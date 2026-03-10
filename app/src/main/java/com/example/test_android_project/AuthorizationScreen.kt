package com.example.test_android_project

import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test_android_project.ui.theme.PinkSystemColor

@Composable
fun AuthorizationScreen(
    navController: NavController
){
    var usersEmail by remember{mutableStateOf("")}
    var isEmailFieldValid by remember{mutableStateOf(true)}
    var isEmailTouched by remember{mutableStateOf(false)}

    var usersPassword by remember{mutableStateOf("")}
    var passwordError by remember{mutableStateOf(false)}

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            modifier = Modifier.offset(y = -100.dp, x = 0.dp)
        )
        Column (
            modifier = Modifier
                .padding(25.dp, 300.dp, 25.dp, 0.dp)
        ){
            Text(
                text = "Авторизация",
                fontSize = 38.sp,
                fontFamily = FontFamily(Font(R.font.system_font))
            )
            Spacer(Modifier.height(20.dp))
            EmailField(
                email = usersEmail,
                isEmailValid = isEmailFieldValid,
                isTouched = isEmailTouched,
                onEmailChange = {
                    usersEmail = it
                    isEmailTouched = true
                    isEmailFieldValid = EMAIL_ADDRESS.matcher(it).matches() && it.isNotEmpty()
                },
                onClearClicked = {
                    usersEmail = ""
                    isEmailTouched = false
                    isEmailFieldValid = true
                }
            )
            Spacer(Modifier.height(12.dp))
            PasswordField(
                password = usersPassword,
                isErrorPassword = passwordError,
                onPasswordChange = {
                    usersPassword = it
                },
                onClearClicked = {
                    usersPassword = ""
                    passwordError = false
                }
            )
            Spacer(Modifier.height(100.dp))
            CommonButton(
                text = "Войти в аккаунт",
                onButtonClick = {
                    isEmailTouched = true
                    isEmailFieldValid = true
                    passwordError = false

                    var isValid = true

                    if (usersEmail.isEmpty() || !EMAIL_ADDRESS.matcher(usersEmail).matches()) {
                        isEmailFieldValid = false
                        isValid = false
                    }
                    if (usersPassword.isEmpty()) {
                        passwordError = true
                        isValid = false
                    }

                    if (isValid) {
                        navController.navigate("listOfOrganizations")
                    }
                }
            )
            Spacer(Modifier.height(5.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                ToOtherScreenButton(
                    defaultText = "Еще нет аккаунта?",
                    pointedText = "Создайте его!"
                ) {
                    navController.navigate("registration")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthorizationScreenPreview(){
    AuthorizationScreen(rememberNavController())
}

