package com.example.musicappui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.MainViewModel
import com.example.musicappui.Navigation
import com.example.musicappui.R
import com.example.musicappui.Screen
import com.example.musicappui.screenInBottom
import com.example.musicappui.screenInDrawer
import com.example.musicappui.ui.theme.AccountDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val viewModel: MainViewModel = viewModel()
    val currentScreen = viewModel.currentScreen.value
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isSheetFullScreen by remember {
        mutableStateOf(false)
    }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded })
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp
    val title = remember {
        mutableStateOf(currentScreen.title)
    }
    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(modifier = Modifier.wrapContentSize()) {
                screenInBottom.forEach {
                    val selected = (currentRoute == it.bRoute)
                    val tint = if (selected) Color.White else Color.Black
                    BottomNavigationItem(
                        selected = (currentRoute == it.bTitle),
                        onClick = { navController.navigate(it.bRoute)
                                  title.value = it.bTitle},
                        icon = {
                            Icon(
                                tint = tint,
                                painter = painterResource(id = it.icon),
                                contentDescription = it.bTitle
                            )
                        },
                        label = { Text(text = it.bTitle, color = tint) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius
        ),
        sheetContent = {
            MoreBottomSheet(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            )
        }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = title.value) },
                    actions = { IconButton(onClick = {
                        scope.launch{
                            if (modalSheetState.isVisible){
                                modalSheetState.hide()
                            }else{
                                modalSheetState.show()
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                    }},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null
                            )
                        }
                    }
                )
            },
            bottomBar = bottomBar,
            scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(screenInDrawer) {
                        DrawerItem(
                            selected = (currentRoute == it.route),
                            onClicked = {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                                if (it.route == Screen.DrawScreen.AddAccount.route) {
                                    //show dialog
                                    dialogOpen.value = true
                                } else {
                                    navController.navigate(it.route)
                                    title.value = it.dTitle
                                }
                            },
                            item = it
                        )
                    }
                }

            }
        ) {
            Navigation(viewModel = viewModel, navController = navController, pd = it)
            AccountDialog(dialogOpen = dialogOpen)
        }
    }


}

@Composable
fun DrawerItem(
    selected: Boolean,
    onClicked: () -> Unit,
    item: Screen.DrawScreen
) {
    val background = if (selected) Color.Gray else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(color = background)
            .clickable { onClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(painter = painterResource(id = item.icon), contentDescription = item.dTitle)
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

    }
}

@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.primary)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "setting"
                )
                Text(text = "Setting", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}