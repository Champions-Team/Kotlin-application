package com.example.test_android_project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
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
fun MainScreen(navController: NavController) {

    var searchState by remember {mutableStateOf("")}

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background_image),
            contentDescription = null,
            modifier = Modifier.offset(y = -400.dp, x = 0.dp)
        )
        Column(
            modifier = Modifier
                .padding(25.dp, 45.dp, 25.dp, 0.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Column {
                    Text(
                        text = "Привет,",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.system_font))
                    )
                    Text(
                        text = "Пользователь",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.system_font))
                    )
                }
                UserProfileButton()
            }
            Spacer((Modifier.height(205.dp)))
            SearchField(
                searchState = searchState,
                onSearchChange = {
                    searchState = it
                },
                onClearClicked = {
                    searchState = ""
                }
            )
            Spacer(Modifier.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Поможем вместе",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.system_font)),
                    color = Color.Gray
                )
                Spacer(Modifier.width(15.dp))
                CommonTextButton(
                    defaultText = "",
                    pointedText = "Показать все..."
                ){
                    navController.navigate("listOfOrganizations")
                }
            }
            Spacer(Modifier.height(6.dp))
            HorizontalListOfOrganizations()
        }
    }
}
@Composable
fun HorizontalListOfOrganizations(){
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(16){
            Column(
                modifier = Modifier
                    .height(240.dp)
                    .width(160.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        color = GraySystemColor,
                        shape = RoundedCornerShape(35.dp)
                    )
            ){
                VerticalCardOfOrganization()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview(){
    MainScreen(rememberNavController())
}