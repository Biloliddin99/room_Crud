package com.example.room_crud.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_crud.models.Student

@Database(entities = [Student::class], version = 1)
abstract class MyDbHelper : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        fun newInstance(context: Context): MyDbHelper {
            return Room.databaseBuilder(context,MyDbHelper::class.java,"my_Student_db")
                .allowMainThreadQueries()
                .build()
        }
    }
}