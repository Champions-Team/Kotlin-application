package com.example.test_android_project

import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test_android_project.ui.theme.PinkSystemColor
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

@Composable
fun RegistrationScreen(navController: NavController, dataStoreManager: DataStoreManager) {
    var usersEmail by remember { mutableStateOf("") }
    var isEmailFieldValid by remember { mutableStateOf(true) }
    var isEmailTouched by remember { mutableStateOf(false) }

    var usersPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    var userName by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val view = LocalView.current
    val gson = Gson()
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background_image),
            contentDescription = null,
            modifier = Modifier.offset(y = -170.dp, x = 0.dp)
        )
        Column(modifier = Modifier.padding(25.dp, 240.dp, 25.dp, 0.dp)) {
            Text(
                text = "Регистрация",
                fontFamily = FontFamily(Font(R.font.system_font)),
                fontSize = 38.sp
            )
            Spacer(Modifier.height(20.dp))
            NameField(
                username = userName,
                isErrorName = nameError,
                onUsernameChange = { userName = it },
                onClearClicked = {
                    userName = ""
                    nameError = false
                }
            )
            Spacer(Modifier.height(12.dp))
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
                onPasswordChange = { usersPassword = it },
                onClearClicked = {
                    usersPassword = ""
                    passwordError = false
                }
            )
            Spacer(Modifier.height(62.dp))
            CommonButton(
                text = "Создать аккаунт",
                enabled = !isLoading,
                onButtonClick = {
                    isEmailTouched = true
                    passwordError = false
                    nameError = false

                    var isValid = true

                    if (usersEmail.isEmpty() || !EMAIL_ADDRESS.matcher(usersEmail).matches()) {
                        isEmailFieldValid = false
                        isValid = false
                    }
                    if (usersPassword.isEmpty()) {
                        passwordError = true
                        isValid = false
                    }
                    if (userName.isEmpty()) {
                        nameError = true
                        isValid = false
                    }

                    if (isValid) {
                        isLoading = true
                        val json = """
                            {
                                "name": "$userName",
                                "email": "$usersEmail",
                                "password": "$usersPassword"
                            }
                        """.trimIndent()

                        val mediaType = "application/json; charset=utf-8".toMediaType()
                        val body = json.toRequestBody(mediaType)

                        val request = Request.Builder()
                            .url("https://postman-echo.com/post")
                            .post(body)
                            .addHeader("Content-Type", "application/json")
                            .build()

                        client.newCall(request).enqueue(object : Callback {
                            override fun onFailure(call: Call, e: IOException) {
                                view.post {
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        "Ошибка сети: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onResponse(call: Call, response: Response) {
                                view.post {
                                    isLoading = false
                                    val responseBody = response.body?.string()
                                    when (response.code) {
                                        201 -> {
                                            try {
                                                val tokenResponse = gson.fromJson(responseBody, TokenResponse::class.java)

                                                coroutineScope.launch {
                                                    dataStoreManager.saveTokens(
                                                        accessToken = tokenResponse.access_token,
                                                        refreshToken = tokenResponse.refresh_token,
                                                        tokenType = tokenResponse.token_type,
                                                        userEmail = usersEmail
                                                    )
                                                }

                                                Toast.makeText(
                                                    context,
                                                    "Аккаунт успешно создан!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                navController.navigate("listOfOrganizations")
                                            } catch (e: Exception) {
                                                Toast.makeText(context, "Аккаунт создан, но ошибка сохранения данных", Toast.LENGTH_SHORT).show()
                                                navController.navigate("listOfOrganizations")
                                            }
                                        }
                                        400 -> {
                                            Toast.makeText(context, "Неверные данные запроса или пользователь уже существует", Toast.LENGTH_LONG).show()
                                        }
                                        else -> {
                                            Toast.makeText(context, "Ошибка сервера: ${response.code}", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                    response.close()
                                }
                            }
                        })
                    }
                }
            )
            Spacer(Modifier.height(5.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CommonTextButton(
                    defaultText = "Уже есть аккаунт?",
                    pointedText = "Войдите в него!"
                ) {
                    navController.navigate("authorization")
                }
            }
        }
    }
}

@Composable
fun NameField(
    username: String,
    isErrorName: Boolean,
    onUsernameChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Text(
        text = "Ваше имя",
        fontFamily = FontFamily(Font(R.font.system_font)),
        fontSize = 15.sp
    )
    Spacer(Modifier.height(12.dp))
    OutlinedTextField(
        value = username,
        onValueChange = {
            onUsernameChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text = "Vsevolod_Kuznetsov",
                fontFamily = FontFamily(Font(R.font.system_font)),
                fontSize = 12.sp,
                color = if( !isErrorName ) {
                    Color.Gray
                } else{
                    Color.Red
                }
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = PinkSystemColor,
            focusedBorderColor = PinkSystemColor,
            errorBorderColor = Color.Red,
            errorTrailingIconColor = Color.Red
        ),
        textStyle = TextStyle(fontSize = 12.sp),
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
        isError = isErrorName,
        modifier = Modifier
            .width(343.dp)
            .height(55.dp)
    )
}

@Composable
fun EmailField(
    email: String,
    isEmailValid: Boolean,
    isTouched: Boolean,
    onEmailChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Text(
        text = "Email",
        fontFamily = FontFamily(Font(R.font.system_font)),
        fontSize = 15.sp
    )
    Spacer(Modifier.height(12.dp))
    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text ="example@gmail.com",
                fontFamily = FontFamily(Font(R.font.system_font)),
                fontSize = 12.sp,
                color = if( isEmailValid ) {
                    Color.Gray
                } else{
                    Color.Red
                }
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = PinkSystemColor,
            focusedBorderColor = PinkSystemColor,
            errorBorderColor = Color.Red,
            errorTrailingIconColor = Color.Red
        ),
        textStyle = TextStyle(fontSize = 12.sp),
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
        isError = !isEmailValid && isTouched,
        modifier = Modifier
            .width(343.dp)
            .height(55.dp)
    )
}

@Composable
fun PasswordField(
    password: String,
    isErrorPassword: Boolean,
    onPasswordChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    Text(
        text = "Пароль",
        fontFamily = FontFamily(Font(R.font.system_font)),
        fontSize = 15.sp
    )
    Spacer(Modifier.height(12.dp))
    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text = "example_password",
                fontFamily = FontFamily(Font(R.font.system_font)),
                fontSize = 12.sp,
                color = if( !isErrorPassword ) {
                    Color.Gray
                } else{
                    Color.Red
                }
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = PinkSystemColor,
            focusedBorderColor = PinkSystemColor,
            errorBorderColor = Color.Red,
            errorTrailingIconColor = Color.Red
        ),
        textStyle = TextStyle(fontSize = 12.sp),
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
        isError = isErrorPassword,
        modifier = Modifier
            .width(343.dp)
            .height(55.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview(){
    val fakeDataStoreManager = DataStoreManager(LocalContext.current)
    RegistrationScreen(rememberNavController(), fakeDataStoreManager)
}