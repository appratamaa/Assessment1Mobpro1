package org.d3if3156.nakos.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainscreen")
    data object About: Screen("aboutscreen")
}