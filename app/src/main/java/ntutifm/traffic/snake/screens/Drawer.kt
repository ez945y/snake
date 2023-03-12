package ntutifm.traffic.snake.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ntutifm.traffic.snake.R

sealed class DrawerScreens(val title: String, val route: String) {
    object Main : DrawerScreens("Main", "Main")
}

private val screens = listOf(
    DrawerScreens.Main,
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
    context: Context,
) {
    Card(modifier = Modifier.padding(end = 50.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)) {
        Column(
            modifier
                .fillMaxSize()
                .padding(start = 15.dp, top = 48.dp)
        ) {
            Row {
                Icon(painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 3.dp))
                Spacer(modifier = Modifier.padding(4.dp))
                Text("選單",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(start = 10.dp))
            }

            Divider(color = MaterialTheme.colorScheme.background,
                thickness = 1.dp, modifier = Modifier.padding(top = 20.dp))
            screens.forEach { screen ->
                Spacer(Modifier.height(24.dp))
                Row(Modifier
                    .padding(end = 20.dp)
                    .clickable(onClick = { onDestinationClicked(screen.route) })) {
                    Icon(Icons.Outlined.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(top = 2.dp))
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = screen.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.background
                    )

                }
            }
            Divider(color = MaterialTheme.colorScheme.background,
                thickness = 1.dp, modifier = Modifier.padding(top = 40.dp))
        }
    }
}