package com.memobiles.architectureexample.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.memobiles.architectureexample.utilites.runOnIoThread

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                           // WorkManager.getInstance().enqueue(request)
                            populateNoteAsyncTask(instance!!).execute()
                        }
                    })
                    .build()
        }
    }

    class populateNoteAsyncTask(private var noteDao:NoteDao?):AsyncTask<Void,Void,Void>(){
        constructor(noteDatabase: NoteDatabase):this(noteDatabase.noteDao())

        override fun doInBackground(vararg params: Void?): Void? {
            noteDao?.insert(Note(title = "Title 1",description = "descriptin1",priority = 2))
            noteDao?.insert(Note(title = "Title 3",description = "descriptin6",priority = 9))
            noteDao?.insert(Note(title = "Title 4",description = "descriptin7",priority = 8))
            noteDao?.insert(Note(title = "Title 5",description = "descriptin8",priority = 7))
            return null
        }

    }
}