package ntutifm.traffic.snake.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import ntutifm.traffic.snake.R
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.roundToInt

@Composable
fun MainScreen(
    navController: NavController,
    openDrawer: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetY by remember { mutableStateOf(0f) }
        val hightMax = -900f
        val currenHeight = remember { mutableStateOf(300.dp) }
        Box {
            Image(painterResource(id = R.drawable.map), null, modifier = Modifier
                .fillMaxSize(), alignment = Alignment.TopCenter)
            val text = remember { mutableStateOf("CLICK ME") }
            Button(onClick = {
                text.value = "CLICKED"
                openDrawer()
                //navController.navigate("Main")
            }, modifier = Modifier.padding(150.dp)) {
                Text(text.value)
            }
        }
        Box(modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .height(currenHeight.value)
            .align(
                Alignment.BottomCenter)) {
            Card(backgroundColor = Color.White,
                modifier = Modifier.padding(top = 30.dp, start = 30.dp)) {
                Row {
                    val string1 = "承德路"
                    val string2 = "市民大道"
                    Icon(Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 3.dp, start = 10.dp))
                    Icon(Icons.Filled.Refresh,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 3.dp, start = 10.dp))
                    Icon(Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.padding(top = 3.dp, start = 10.dp))
                    Text(text = "$string1 X $string2",
                        modifier = Modifier.padding(start = 50.dp, end = 10.dp))
                }
            }
        }
        Box(
            Modifier
                .padding(top = 500.dp, start = 145.dp)
                .offset { IntOffset(0, offsetY.roundToInt()) }
                .background(Color.White)
                .height(10.dp)
                .width(120.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        if ((dragAmount.y > 0 || offsetY > hightMax) && (dragAmount.y < 0 || offsetY < 0f)) {
                            offsetY += dragAmount.y
                            currenHeight.value -= dragAmount.y.toDp()
                        }
                    }
                }
        )
    }
}