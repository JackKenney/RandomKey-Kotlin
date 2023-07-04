package dev.kenney.randomkey.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.kenney.randomkey.KeySelector

var keySelector = KeySelector()

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    KeyView()
                }
            }
        }
    }
}

@Composable
fun KeyView() {
    val state: MutableState<Map<String, Array<String>>> =
        remember { mutableStateOf(keySelector.sampleKey()) }

    fun updateKey() {
        state.value = keySelector.sampleKey()

    }

    Button(onClick = ::updateKey) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = state.value["root"]?.get(0) ?: "",
                    fontSize = 275.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0..6) {
                    NoteColumn(i, state)
                }
            }
        }
    }
}

@Composable
fun NoteColumn(i: Int, state: MutableState<Map<String, Array<String>>>) {
    val colors = arrayOf(
        Color(255, 255, 50),
        Color(50, 255, 255),
    )
    Column {
        Text("", fontSize = 50.sp)
        Text(
            "" + state.value["notes"]?.get(i) + (if (i != 6) "\t\t\t\t" else ""),
            textAlign = TextAlign.Left,
            fontSize = 35.sp,
            fontFamily = FontFamily.Monospace,
            color = colors[i % 2]
        )
        Text(
            state.value["major"]?.get(i) + "",
            textAlign = TextAlign.Left,
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            color = colors[i % 2]
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        KeyView()
    }
}
