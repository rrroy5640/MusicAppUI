package com.example.musicappui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawScreen.Account)
    val currentScreen = _currentScreen
    fun setCurrentState (screen: Screen){
        _currentScreen.value = screen
    }
}