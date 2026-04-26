package com.example.financeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User entidad que representa a un usuario en la base de datos
 *@Entity room que marca esta clase como una tabla de SQlite
 */

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0,
    val name: String,
    val email: String,
    val passwordHash:String,
    val createAt:Long =System.currentTimeMillis()
)
