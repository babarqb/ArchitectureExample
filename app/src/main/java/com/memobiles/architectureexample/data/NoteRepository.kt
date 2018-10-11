package com.memobiles.architectureexample.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.memobiles.architectureexample.utilites.runOnIoThread
import java.util.concurrent.Executors

class NoteRepository private constructor(private var noteDao: NoteDao? = null,
                                         private var allNotes: LiveData<List<Note>>? = null){

    fun insertNote(note:Note){
        runOnIoThread {
            noteDao?.insert(note)
        }
    }
    fun updateNote(note:Note){
        runOnIoThread {
            noteDao?.update(note)
        }
    }
    fun deleteNote(note:Note){
        runOnIoThread {
            noteDao?.delete(note)
        }
    }

    fun deleteAllNotes(){
        runOnIoThread {
            noteDao?.deleteAllNotes()
        }
    }
    fun getAllNotes():LiveData<List<Note>>{

          return allNotes!!

    }
    constructor(application:Application) : this() {
        val database = NoteDatabase.getInstance(application)
        noteDao = database.noteDao()
        allNotes = noteDao?.getAllNotes()
    }
    companion object {
        // For Singleton instantiation
        @Volatile private var instance: NoteRepository? = null
        fun getInstance(application:Application): NoteRepository {
            return NoteRepository.instance ?: synchronized(this) {
                NoteRepository.instance
                        ?: NoteRepository(application).also { NoteRepository.instance = it }
            }
        }

//        private val roomCallBack: Callback = object: Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                instance.insert()
//            }
//        }
    }
}
