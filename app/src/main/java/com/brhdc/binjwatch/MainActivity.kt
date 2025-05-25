package com.brhdc.binjwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.brhdc.binjwatch.presentation.screens.details.DetailsScreen
import com.brhdc.binjwatch.presentation.screens.home.HomeScreen
import com.brhdc.binjwatch.ui.theme.BinjwatchTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BinjwatchTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenHome) {
                    composable<ScreenHome> {
                        HomeScreen(
                            navigateToDetails = { movieId ->
                                navController.navigate(ScreenDetails(movieId))
                            }
                        )
                    }
                    composable<ScreenDetails> { backStackEntry ->
                        val args = backStackEntry.toRoute<ScreenDetails>()
                        DetailsScreen(
                            movieId = args.movieId
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenHome

@Serializable
data class ScreenDetails(
    val movieId: String
)