package com.example.compose_study.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.compose_study.domain.model.Todo

@Database(entities = [Todo::class], version = 2)
abstract class TodoDataBase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}