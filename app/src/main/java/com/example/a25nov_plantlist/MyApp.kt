package com.example.a25nov_plantlist

import android.app.Application
import androidx.room.Room
import com.example.a25nov_plantlist.db.TaskDatabase

class MyApp : Application() {
         lateinit var database: TaskDatabase
            private set

        override fun onCreate() {
            super.onCreate()
            database = Room.databaseBuilder(
                this,
                TaskDatabase::class.java,
                "task_database"
            ).build()
        }

}