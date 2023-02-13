package com.example.compose_study.ui.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_study.domain.model.Todo
import com.example.compose_study.domain.repository.TodoRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val todoRepository: TodoRepository)
    : AndroidViewModel(application){
    private val _items = mutableStateOf(emptyList<Todo>())
    val items : State<List<Todo>> = _items

    private var recentlyDeleteTodo: Todo? = null

    fun addTodo(text : String){
        viewModelScope.launch {
            Log.d("dd", "ghghgh $text")
            todoRepository.addTodo(Todo(title = text))
        }
    }

    fun toggle(uid:Int){
        val todo = _items.value.find { todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.updateTodo(it.copy(isDone = !it.isDone).apply {
                    this.uid = it.uid
                })
            }
        }
    }

    fun delete(uid:Int){
        val todo = _items.value.find { todo -> todo.uid == uid }
        todo?.let {
            viewModelScope.launch {
                todoRepository.deleteTodo(it)
                recentlyDeleteTodo = it
            }
        }
    }

    fun restoreTodo(){
        viewModelScope.launch {
            todoRepository.addTodo(recentlyDeleteTodo ?: return@launch)
            recentlyDeleteTodo = null
        }
    }
}