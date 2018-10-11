package com.memobiles.architectureexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="note_table")
class Note(
        @PrimaryKey(autoGenerate = true) val id: Int =0,
        val title:String,
        val description:String,
        val priority:Int
)