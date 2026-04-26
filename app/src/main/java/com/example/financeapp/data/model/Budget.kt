package com.example.financeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que va a representar el presupuesto entity base de datos
 *
 */

@Entity(tableName ="budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId:Long,
    val category: ExpenseCategory,
    val montlyLimit: Double,
    val mounth: Int,
    val year: Int,
    val createAt:Long =System.currentTimeMillis()
)
