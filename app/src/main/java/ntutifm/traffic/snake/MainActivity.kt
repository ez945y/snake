package ntutifm.traffic.snake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import kotlinx.coroutines.launch
import ntutifm.traffic.snake.screens.Drawer
import ntutifm.traffic.snake.screens.DrawerScreens
import ntutifm.traffic.snake.screens.MainScreen
import ntutifm.traffic.snake.ui.theme.SnakeTheme
import ntutifm.traffic.snake.ui.theme.isLight

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SnakeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    rememberSystemUiController().setStatusBarColor(
                        Color.Transparent,
                        darkIcons = androidx.compose.material3.MaterialTheme.colorScheme.isLight())

                    //dbReStart(LocalContext.current)
                    //auth = Firebase.auth
                    val navController = rememberNavController()
                    val drawerState =
                        androidx.compose.material3.rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                    val context = LocalContext.current
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen,
                        drawerContent = {
                            Drawer(
                                onDestinationClicked = { route ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(route) {
                                        popUpTo = navController.graph.startDestinationId
                                        launchSingleTop = true
                                    }
                                }, context = context)
                        },

                        ) {
                        NavHost(
                            navController = navController,
                            startDestination = "Main"
                        ) {
                            composable(route = DrawerScreens.Main.route) {
                                MainScreen(
                                    navController = navController,
                                    openDrawer = {
                                        openDrawer()
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}
