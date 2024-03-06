package com.example.musicappui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicappui.ui.theme.AccountDialog
import com.example.musicappui.ui.theme.AccountView
import com.example.musicappui.ui.theme.Subscription

@Composable
fun Navigation(viewModel: ViewModel, navController: NavHostController, pd: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawScreen.Account.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(route = Screen.DrawScreen.Subscription.route) {
            Subscription()
        }
        composable(route = Screen.DrawScreen.Account.route){
            AccountView()
        }
    }
}