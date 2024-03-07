package com.example.musicappui

import androidx.annotation.DrawableRes

data class LibItem(@DrawableRes val icon: Int, val name: String)

val libItems = listOf<LibItem>(
    LibItem(R.drawable.ic_music_icon, "playlist"),
    LibItem(R.drawable.ic_subscribe, "artist"),
    LibItem(R.drawable.baseline_library_music_24, "album")
)