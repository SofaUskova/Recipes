package com.example.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipes.navigation.BottomBarNavigation
import com.example.recipes.navigation.BottomBarNavigationScreen
import com.example.recipes.ui.theme.Black
import com.example.recipes.ui.theme.PrimaryTransparent20
import com.example.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesTheme {

                val bottomNavigation = BottomBarNavigation()
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = PrimaryTransparent20,
                            contentColor = Black,
                            elevation = 0.dp
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            bottomNavigation.items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = screen.iconId),
                                            contentDescription = stringResource(id = screen.labelId)
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
                ) { innerPadding -> bottomNavigation.BottomBarNavHost(navController, innerPadding) }

            }
        }
    }
}