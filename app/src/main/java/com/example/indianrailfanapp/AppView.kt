package com.example.indianrailfanapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.indianrailfanapp.components.AppBottomBar
import com.example.indianrailfanapp.data.Locomotive
import com.example.indianrailfanapp.views.CompareView
import com.example.indianrailfanapp.views.GalleryView
import com.example.indianrailfanapp.views.HomeView
import com.example.indianrailfanapp.views.LocoDetailScreen
import com.example.indianrailfanapp.views.SearchView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppView() {
    val navController = rememberNavController()

    val viewModel: MainViewModel=viewModel()
    val viewState by viewModel.locoState

    //Get the current screen to check if the bottom bar should be shown
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //The list of screens that should show bottom bar
    val bottomBarRoutes = setOf(Screen.Home.route, Screen.Search.route, Screen.Gallery.route, Screen.Compare.route)
    val shouldShowBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            //Conditionally display the bottom bar
            if (shouldShowBottomBar) {
                AppBottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeView(viewState=viewState, navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("locomotive",it)
                    navController.navigate(Screen.LocoDetail.route)
                })
            }
            composable(Screen.Search.route) { SearchView() }
            composable(Screen.Gallery.route) { GalleryView() }
            composable(Screen.Compare.route) { CompareView() }

            //This is the screen that will NOT have the bottom bar
            composable(Screen.LocoDetail.route){
                val loco = navController.previousBackStackEntry?.savedStateHandle?.
                get<Locomotive>("locomotive") ?: Locomotive("","","","","","","","","")
                LocoDetailScreen(loco)
            }
        }
    }
}


