package com.example.test_android_project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test_android_project.ui.theme.PinkSystemColor


@Composable
fun CommonButton(
    text: String,
    enabled: Boolean = true,
    onButtonClick: (String) -> Unit
){
    Button(
        shape = RoundedCornerShape(17.dp),
        onClick = {
            onButtonClick(text)
        },
        enabled = enabled,
        border = BorderStroke(1.dp, PinkSystemColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = PinkSystemColor,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 15.dp)
    ){
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.system_font)),
        )
    }
}
@Composable
fun CommonTextButton(
    defaultText: String,
    pointedText: String,
    onClicked: () -> Unit
){
    Button(
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background
        ),

        ){
        Text(
            text = defaultText,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.system_font)),
            color = Color.Gray

        )
        Text(
            text = " $pointedText",
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.system_font)),
            color = PinkSystemColor
        )
    }
}
@Composable
fun SearchField(
    searchState: String,
    onSearchChange: (String) -> Unit,
    onClearClicked: () -> Unit
){
    OutlinedTextField(
        value = searchState,
        onValueChange = {
            onSearchChange(it)
        },
        shape = RoundedCornerShape(17.dp),
        placeholder = {
            Text(
                text = "Поиск",
                fontFamily = FontFamily(Font(R.font.system_font)),
                fontSize = 12.sp,
                color = Color.Gray
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = PinkSystemColor,
            focusedBorderColor = PinkSystemColor
        ),
        textStyle = TextStyle(fontSize = 12.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search icon for search field"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onClearClicked()
                }
            ){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear icon for search field"
                )
            }
        },
        modifier = Modifier
            .width(343.dp)
            .height(55.dp)
    )
}
@Composable
fun VerticalCardOfOrganization(){
    Button(
        onClick = {},
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RectangleShape,
        modifier = Modifier
            .height(240.dp)
            .width(160.dp)
    ){
        Column {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Organization photo",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .height(130.dp)
                    .width(160.dp)
                    .background(
                        color = PinkSystemColor,
                        shape = RoundedCornerShape(35.dp)
                    )
            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp)
            ) {
                Text(
                    text = "МГТУ Станкин",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.system_font)),
                    color = Color.Black
                )
                Text(
                    text = "Описание..Описание..Описание..Описание.." +
                            "Описание..Описание..Описание..Описание..",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.system_font)),
                    color = Color.Gray
                )
            }
        }
    }
}
@Composable
fun HorizontalCardOfOrganization(){
    Button(
        onClick = {},
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .height(150.dp)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Organization photo",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .height(130.dp)
                    .width(160.dp)
                    .background(
                        color = PinkSystemColor,
                        shape = RoundedCornerShape(35.dp)
                    )
            )
            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp)
                    .height(150.dp)
            ) {
                Text(
                    text = "МГТУ Станкин",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.system_font)),
                    color = Color.Black
                )
                Text(
                    text = "Описание..Описание..Описание..Описание.." +
                            "Описание..Описание..Описание..Описание.." +
                            "Описание..Описание..Описание..Описание.." +
                            "Описание..Описание..Описание..",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.system_font)),
                    color = Color.Gray
                )
            }
        }
    }
}
@Composable
fun UserProfileButton(){
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, 45.dp)
    ){
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(60.dp)
        ){
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = "User's account",
                tint = Color.Unspecified,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
private fun CommonButtonPreview(){
    CommonButton("click", true, {""})
}
@Composable
@Preview(showBackground = true)
private fun CommonTextButtonPreview(){
    CommonTextButton("some button text", "some button text") { }
}
@Composable
@Preview(showBackground = true)
private fun SearchFieldPreview(){
    SearchField("", {}, {})
}
@Composable
@Preview(showBackground = true)
private fun VerticalCardOfOrganizationPreview(){
    VerticalCardOfOrganization()
}
@Composable
@Preview(showBackground = true)
private fun HorizontalCardOfOrganizationPreview(){
    HorizontalCardOfOrganization()
}
@Composable
@Preview(showBackground = true)
private fun UserProfileButtonPreview(){
    UserProfileButton()
}