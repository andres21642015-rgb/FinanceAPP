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

    /**
     * Inserta un nuevo presupuesto en la base de datos.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: Budget)

    /**
     * Obtiene todos los presupuestos de un usuario en un mes y año específico.
     */
    @Query("SELECT * FROM budgets WHERE userId = :userId AND month = :month AND year = :year")
    fun getBudgetsByMonthYear(userId: Long, month: Int, year: Int): Flow<List<Budget>>

    /**
     * Obtiene un presupuesto específico por usuario, categoría, mes y año.
     */
    @Query("""
        SELECT * FROM budgets 
        WHERE userId = :userId
        AND category = :category 
        AND month = :month 
        AND year = :year 
        LIMIT 1
    """)
    suspend fun getBudgetByCategoryMonthYear(
        userId: Long,
        category: ExpenseCategory,
        month: Int,
        year: Int
    ): Budget?

    /**
     * Actualiza un presupuesto existente.
     */
    @Update
    suspend fun update(budget: Budget)

    /**
     * Elimina un presupuesto de la base de datos.
     */
    @Delete
    suspend fun delete(budget: Budget)
}