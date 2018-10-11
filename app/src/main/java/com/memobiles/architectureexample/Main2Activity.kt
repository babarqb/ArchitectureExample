package com.memobiles.architectureexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.memobiles.architectureexample.data.Note
import com.memobiles.architectureexample.viewmodels.NoteViewModel

class Main2Activity : AppCompatActivity() {
    private var noteViewModel:NoteViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        //noteViewModel!!.insert(Note(title="Babar",description = "description",priority = 9))
        noteViewModel?.getAllNotes()?.observe(this, Observer<List<Note>> {
            Toast.makeText(this@Main2Activity,"${it}onChanged", Toast.LENGTH_LONG).show()
        })
    }
}
