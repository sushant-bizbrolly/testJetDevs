package com.imaginato.homeworkmvvm.data.local.demo

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Demo")
data class Demo constructor(
    @PrimaryKey
    @NonNull
    val id: String,
    @ColumnInfo(name = "xAcc") var xAcc: String,
    @ColumnInfo(name = "userId") var userId: String,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "isDeleted") var isDeleted: Boolean,
)