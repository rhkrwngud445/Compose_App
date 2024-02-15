package com.app.compose_study.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.compose_study.data.model.Post
import com.app.compose_study.presentation.ui.radio.RadioScreen
import com.app.compose_study.presentation.ui.library.LibraryScreen
import com.app.compose_study.presentation.ui.home.HomeScreen

@Composable
fun DisneyComposeApp() {
    val navController = rememberNavController()
    val items = listOf(
        NavScreen.Home,
        NavScreen.Radio,
        NavScreen.Library,
    )
    Scaffold(
        topBar = {
            MainTopBar()
        },
        bottomBar = {
            MainBottomNavigation(navController, items)
        }
    ) { innerPadding ->
        NavHost(navController, innerPadding)
    }
}

@Composable
private fun MainTopBar() {
    TopAppBar(
        title = { Text(text = "DisneyCompose", fontSize = 20.sp, color = Color.White) },
    )
}

@Composable
private fun MainBottomNavigation(
    navController: NavHostController,
    items: List<NavScreen>
) {
    androidx.compose.material.BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        screen.image,
                        contentDescription = null,
                        Modifier
                            .width(36.dp)
                            .height(36.dp)
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun NavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: MainViewModel = hiltViewModel()
) {
    androidx.navigation.compose.NavHost(
        navController,
        startDestination = NavScreen.Home.route,
        Modifier.padding(innerPadding)
    ) {
        composable(NavScreen.Home.route) { HomeScreen(navController, listOf(Post.mock())) }
        composable(NavScreen.Radio.route) { RadioScreen(navController) }
        composable(NavScreen.Library.route) { LibraryScreen(navController) }
    }
}

sealed class NavScreen(
    val route: String, val image: ImageVector
) {
    data object Home : NavScreen("home", Icons.Filled.Home)
    data object Radio :
        NavScreen("radio", Icons.Filled.Radio)

    data object Library :
        NavScreen("library", Icons.Filled.LibraryAdd)
}