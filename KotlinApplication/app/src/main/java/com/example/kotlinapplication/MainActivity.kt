package com.example.kotlinapplication

import android.os.Bundle
/*import android.widget.EditText*/
import android.widget.LinearLayout
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
/*import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview*/
import androidx.core.view.isVisible
/*import com.example.kotlinapplication.ui.theme.KotlinApplicationTheme*/
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
class MainActivity : ComponentActivity() {
    val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)
        val person_button = findViewById<Button>(R.id.btnPerson)
        val back_button = findViewById<ImageButton>(R.id.btnBack)
        val create_button = findViewById<Button>(R.id.btnCreate)
        val authorization_layout = findViewById<LinearLayout>(R.id.lt_authorization)
        val text = findViewById<TextView>(R.id.tvFirstText)
        val first_layout = findViewById<LinearLayout>(R.id.ltFirst)
        person_button.setOnClickListener{
            first_layout.isVisible = false
            text.isVisible = false
            authorization_layout.isVisible = true
        }
        back_button.setOnClickListener {
            first_layout.isVisible = true
            text.isVisible = true
            authorization_layout.isVisible = false
        }
        create_button.setOnClickListener {
            val request = Request.Builder().url("https://www.pornhub.com/").get().build() // <--- сюда писать
            client.newCall(request)
        }
    }
}
