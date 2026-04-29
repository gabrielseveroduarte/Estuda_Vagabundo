package br.com.estudavagabundo.subject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubjectDao {

    @Insert
    suspend fun insert(subject: SubjectsData)

    @Delete
    suspend fun delete(subject: SubjectsData)

    @Update
    suspend fun update(subject: SubjectsData)

    @Query("SELECT * FROM subjects")
    fun getAll(): LiveData<List<SubjectsData>>
}