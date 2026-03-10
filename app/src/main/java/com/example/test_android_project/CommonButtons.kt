package com.example.test_android_project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
@Preview(showBackground = true)
private fun CommonButtonPreview(){
    CommonButton("click", true, {""})
}