package com.example.room_crud.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_crud.models.Student

@Dao
interface StudentDao {
    @Query("select * from student")
    fun getAllStudent():List<Student>

    @Insert
    fun addStudent(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM student WHERE name LIKE '%' || :searchQuery || '%' OR grade LIKE '%' || :searchQuery")
    fun searchStudent(searchQuery: String):List<Student>

    @Update
    fun editUser(student: Student)

    @Query("select * from Student where name=:name and grade=:grade")
    fun getUserById(name: String, grade: String): Int

}