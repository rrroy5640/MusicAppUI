package com.example.musicappui

import android.transition.Scene
import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
    sealed class DrawScreen(val dTitle: String, dRoute: String, @DrawableRes val icon: Int) :
        Screen(dTitle, dRoute) {
        object Account : DrawScreen("Account", "account", R.drawable.ic_account)
        object Subscription : DrawScreen("Subscription", "subscribe", R.drawable.ic_subscribe)
        object AddAccount :
            DrawScreen("Add Account", "add_account", R.drawable.baseline_person_add_alt_1_24)
    }

    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int) :
        Screen(bTitle, bRoute) {
        object Home : BottomScreen("Home", "home", R.drawable.baseline_home_24)
        object Library : BottomScreen("Library", "library", R.drawable.baseline_library_music_24)
        object Browse : BottomScreen("Browse", "browse", R.drawable.baseline_search_24)
    }
}

val screenInDrawer = listOf(
    Screen.DrawScreen.Account,
    Screen.DrawScreen.Subscription,
    Screen.DrawScreen.AddAccount
)

val screenInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)