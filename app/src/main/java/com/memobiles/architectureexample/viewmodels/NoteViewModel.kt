package com.memobiles.architectureexample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.memobiles.architectureexample.data.Note
import com.memobiles.architectureexample.data.NoteRepository

class NoteViewModel : AndroidViewModel {
    private var noteRepository:NoteRepository
    private var allNotes:LiveData<List<Note>>

    constructor(application: Application) : super(application){
        noteRepository = NoteRepository(application)
        allNotes = noteRepository.getAllNotes()
    }

    fun insert(note: Note){
        noteRepository.insertNote(note)
    }
    fun update(note: Note){
        noteRepository.updateNote(note)
    }
    fun delete(note: Note){
        noteRepository.deleteNote(note)
    }
    fun deleteAllNotes(){
        noteRepository.deleteAllNotes()
    }
    fun getAllNotes():LiveData<List<Note>>{
        return allNotes!!
    }
}