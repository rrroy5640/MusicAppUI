package com.example.musicappui.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicappui.libItems

@Composable
fun LibraryView(){
    LazyColumn(){
        items(libItems){
            LibraryItem(icon = it.icon, name = it.name)
        }
    }
}

@Composable
fun LibraryItem(@DrawableRes icon:Int, name: String){
    Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)){
        Row {
            Icon(painter = painterResource(id = icon), contentDescription = name, modifier = Modifier.padding(horizontal = 8.dp))
            Text(text = name, modifier = Modifier.padding(horizontal = 8.dp))
        }
        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "")
    }
    Divider()
}