package com.example.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import com.example.musicappui.R

@Composable
fun BrowseView(){
    val categories = listOf("Hits", "Yoga", "Workout","Relax","City","R&B", "Jazz", "Soul", "Pop")

    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(categories){
            BrowserItem(category = it, drawable = R.drawable.ic_music_icon)
        }
    }
}