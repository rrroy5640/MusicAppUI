package com.example.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
    sealed class DrawScreen(val dTitle: String, dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        object Account : DrawScreen("Account", "account", R.drawable.ic_account)
        object Subscription : DrawScreen("Subscription", "subscribe", R.drawable.ic_subscribe)
        object AddAccount :
            DrawScreen("Add Account", "add_account", R.drawable.baseline_person_add_alt_1_24)
    }
}

val screenInDrawer = listOf(
        Screen.DrawScreen.Account,
        Screen.DrawScreen.Subscription,
        Screen.DrawScreen.AddAccount
    )