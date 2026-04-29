package com.example.financeapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeapp.data.model.SharedExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface SharedExpenseDao {

    // 1. insert — insertar un gasto compartido
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sharedExpense: SharedExpense)

    // 2. getSharedExpensesByUser — obtener todos los gastos compartidos de un usuario
    @Query("SELECT * FROM SharedExpense WHERE creatorUserId = :userId ORDER BY date DESC")
    fun getSharedExpensesByUser(userId: Long): Flow<List<SharedExpense>>

    // 3. getUnsettledSharedExpenses — obtener solo los gastos pendientes de un usuario
    @Query("""
        SELECT * FROM SharedExpense 
        WHERE creatorUserId = :userId 
        AND settled = 0 
        ORDER BY date DESC
    """)
    fun getUnsettledSharedExpenses(userId: Long): Flow<List<SharedExpense>>

    // 4. update — actualizar un gasto compartido
    @Update
    suspend fun update(sharedExpense: SharedExpense)

    // 5. delete — eliminar un gasto compartido
    @Delete
    suspend fun delete(sharedExpense: SharedExpense)
}