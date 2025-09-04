package com.example.indianrailfanapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val route: String) {
    object Home : Screen("Home", "home")
    object Search : Screen("Search", "search")
    object Gallery : Screen("Gallery", "gallery")
    object Compare : Screen("Compare", "compare")

    object LocoDetail: Screen("LocoDetail","locodetail")
    }


val bottomNavScreens = listOf(
    Screen.Home to Icons.Filled.Home,
    Screen.Search to Icons.Filled.Search,
    Screen.Gallery to Icons.Filled.PhotoLibrary,
    Screen.Compare to Icons.AutoMirrored.Filled.CompareArrows
)
