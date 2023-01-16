package com.example.composeplayground

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.theme.ComposePlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlayGroundTheme {
                myApp()
            }
        }
    }
}

@Composable
fun myApp() {
    var onClickStart by remember {
        mutableStateOf(true)
    }

    if(onClickStart) {
        OnBoardingScreen( onContinueClicked = { onClickStart = false})
    } else {
        Greetings(List(1000){"$it"})
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit
){
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to basic CodeLab!")
            Button(onClick = onContinueClicked ) {
                Text("Continue")
            }
        }
    }
}

@Composable
fun Greetings(names: List<String> = listOf("World", "Compose")) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            LazyColumn{
                items(names) { name ->
                    Greeting(name)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expended by remember {
        mutableStateOf(false)
    }
    val extraPadding = if (expended) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(
                    text = "Hello,",
                    fontWeight = FontWeight.W600
                )
                Text(
                    text = name
                )
            }
            OutlinedButton(onClick = { expended = !expended }) {
                Text(if (expended) "Show Less" else "Show More")
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlayGroundTheme {
        myApp()
    }
}