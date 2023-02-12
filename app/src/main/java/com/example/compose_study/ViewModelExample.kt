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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalComposeUiApi
class ViewModelExample : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = viewModel<ViewModelExampleViewModel>()
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,) {
                Text(
                    viewModel.data.value,
                    fontSize = 30.sp,
                )
                Button(onClick = {
                    viewModel.changeValue()
                }){
                    Text("변경")
                }
            }
        }
    }
}


class ViewModelExampleViewModel : ViewModel(){
    // 외부에서 수정못하게 하기위해 읽기전용으로 둠
    private val _data = mutableStateOf("hello")
    val data: State<String> = _data

    fun changeValue(){
        _data.value = "World"
    }
}