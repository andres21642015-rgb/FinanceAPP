package com.example.financeapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * expense entidad que representa un gasto en la base de datos
 * esta clase almacena todos los gastos que realiza un usuario cada gasto tiene un monto, categoria, descrpcion fecha etc
 */

@Entity(tableName="expenses")
data class Expense(
    @PrimaryKey(autoGenerate= true)
    val id: Long = 0,
    val userId: Long,
    val amount: Double,
    val category: ExpenseCategory,
    val description: String,
    val date: Long =System.currentTimeMillis(),
    val isRecurring: Boolean =false
)
