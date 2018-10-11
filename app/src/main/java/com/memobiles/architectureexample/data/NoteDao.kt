package com.memobiles.architectureexample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    fun insert(note:Note):Unit

    @Update
    fun update(note:Note):Unit

    @Update
    fun delete(note:Note):Unit

    @Query("delete from note_table")
    fun deleteAllNotes():Unit

    @Query("select * from note_table order by priority desc")
    fun getAllNotes():LiveData<List<Note>>
}