package com.example.compose_study.data.data_source

import androidx.room.*
import com.example.compose_study.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    // 플로우는 비동기 데이터 처리에 적합함
    @Query("SELECT * FROM todo ORDER BY date DESC")
    fun todos(): Flow<List<Todo>>

    // [OnConflictStrategy.REPLACE] <- 동일한 아이디 들어오면 교체해주는 옵션
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo:Todo)

    @Delete
    suspend fun delete(todo:Todo)
}