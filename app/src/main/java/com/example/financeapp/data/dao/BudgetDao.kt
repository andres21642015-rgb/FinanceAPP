package com.example.financeapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeapp.data.model.Budget
import com.example.financeapp.data.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    // Inserta un presupuesto. Si ya existe uno con el mismo ID, lo reemplaza.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: Budget)

    // Obtiene todos los presupuestos de un usuario para un mes y año específicos.
    @Query("SELECT * FROM budgets WHERE userId = :userId AND month = :month AND year = :year")
    fun getBudgetsByMonthYear(userId: Long, month: Int, year: Int): Flow<List<Budget>>

    // Obtiene un único presupuesto filtrado por usuario, categoría, mes y año.
    // Usa LIMIT 1 para evitar múltiples resultados.
    @Query("""
        SELECT * FROM budgets 
        WHERE userId = :userId
        AND category = :category 
        AND month = :month 
        AND year = :year 
        LIMIT 1
    """)
    fun getBudgetByCategoryMonthYear(
        userId: Long,
        category: ExpenseCategory,
        month: Int,
        year: Int
    ): Flow<Budget?>

    // Actualiza un presupuesto existente en la base de datos.
    @Update
    suspend fun update(budget: Budget)

    // Elimina un presupuesto de la base de datos.
    @Delete
    suspend fun delete(budget: Budget)
}