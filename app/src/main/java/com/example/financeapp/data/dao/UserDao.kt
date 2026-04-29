package com.example.financeapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeapp.data.model.User
import kotlinx.coroutines.flow.Flow

/**
 * UserDao-data Access objet para la tabla Users
 * DAO= Data Access object (Objeto de acceso a datos)
 * es una interfaz que define todas las operaciones que podemos haces sobres la tabla users
 * @dao marca esta interfaz como DAO
 * ROOM genera automaticamente la implementación de todos los metodos
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT *  FROM users WHERE id = :userId")
    fun getUserById(userId:Long): Flow<User?>

    @Update
    suspend fun update (user: User)

    @Delete
    suspend fun delete(user: User)
}