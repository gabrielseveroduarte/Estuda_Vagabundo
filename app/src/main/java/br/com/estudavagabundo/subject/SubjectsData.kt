package br.com.estudavagabundo.subject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subject: String
)