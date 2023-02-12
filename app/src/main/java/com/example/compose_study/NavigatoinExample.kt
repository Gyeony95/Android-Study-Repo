package com.example.compose_study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@ExperimentalComposeUiApi
class NavigationExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val naviController = rememberNavController()
            NavHost(
                navController = naviController,
                startDestination = "first",
            ){
                composable("first"){
                    FirstScreen(naviController)
                }
                composable("second"){
                    SecondScreen(naviController)
                }
                // backStackEntry 를 통해 넘어오는 인자값  받을수있음
                composable("third/{value}"){ backStackEntry ->
                    ThirdScreen(
                        value = backStackEntry.arguments?.getString("value") ?: "", naviController)
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController){
    val (value, setValue) = remember{
        mutableStateOf("")
    }


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("첫 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {navController.navigate(route = "second")}) {
            Text("두번째")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if(value.isNotEmpty()){
                navController.navigate(route = "third/$value")
            }
        }) {
            Text("세번째")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("두번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {navController.popBackStack()}) {
            Text("뒤로가기")
        }
    }
}

@Composable
fun ThirdScreen(value : String, navController: NavController){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("두번째 화면")
        Spacer(modifier = Modifier.height(16.dp))
        Text(value)
        Button(onClick = {navController.popBackStack()}) {
            Text("뒤로가기")
        }
    }

}
