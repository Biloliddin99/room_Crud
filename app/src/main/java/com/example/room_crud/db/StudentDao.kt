package com.example.room_crud.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.room_crud.models.Student

@Dao
interface StudentDao {
    @Query("select * from student")
    fun getAllStudent():List<Student>

    @Insert
    fun addStudent(student: Student)

    @Delete
    fun delete(student: Student)
}