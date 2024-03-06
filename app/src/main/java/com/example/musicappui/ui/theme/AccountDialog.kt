package com.example.musicappui.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.primarySurface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>) {
    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = { dialogOpen.value = false },
            confirmButton = {
                TextButton(
                    onClick = { dialogOpen.value = false }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { dialogOpen.value = false }) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Text(text = "Add Account")
            },
            text = {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = emailState,
                        modifier = Modifier.padding(top = 16.dp),
                        onValueChange = { emailState = it },
                        label = { Text(text = "Email") })
                    TextField(
                        value = passwordState,
                        modifier = Modifier.padding(top = 8.dp),
                        onValueChange = { passwordState = it },
                        label = { Text(text = "Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primarySurface)
                .padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}