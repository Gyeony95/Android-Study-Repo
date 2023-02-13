package com.example.compose_study.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// 룸 디비에서 사용하기위해 붙이는 어노테이션
@Entity
data class Todo(
    val title: String,
    val date: Long = Calendar.getInstance().timeInMillis, // 현재시간을 밀리세컨드 단위로 저장
    val isDone : Boolean = false,
    ) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}