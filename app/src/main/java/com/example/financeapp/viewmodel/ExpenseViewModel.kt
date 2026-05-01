package com.example.financeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.database.AppDatabase
import com.example.financeapp.data.model.Expense
import com.example.financeapp.data.model.ExpenseCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class ExpenseViewModel(application: Application) : AndroidViewModel(application){
    // INICIALIZACIÓN DEL DAO carga todas las consultas
    private val expenseDao = AppDatabase.getDatabase(application).expenseDao()

    //creando variable privada y publica
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses : StateFlow<List<Expense>> = _expenses

    //inicializable de la variable que muestra los gastos totales
    private val _totalExpenses = MutableStateFlow(0.0)
    val totalExpenses : StateFlow<Double> = _totalExpenses

    //funcion que carga los gastos del usuario una vez ingrese
    fun loadExpenses(userId: Long){
        viewModelScope.launch {
            expenseDao.getExpensesByUser(userId).collect {
                _expenses.value = it
            }
        }
    }
    //funcion para traer los valores de las fechas y cargar los gastos por fecha
    fun loadExpensesByMonth(userId: Long, month: Int, year: Int){
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            calendar.set(year, month - 1, 1,0,0,0)
            val startDate = calendar.timeInMillis

            calendar.add(Calendar.MONTH,1)
            val endDate = calendar.timeInMillis

            expenseDao.getExpensesByDateRange(userId,startDate,endDate).collect {
                _expenses.value = it
            }

            expenseDao.getTotalExpensesByDateRange(userId, startDate,endDate).collect {
                _totalExpenses.value = it
            }
        }
    }

    fun addExpense(userId: Long, amount: Double, category: ExpenseCategory, description: String){
        viewModelScope.launch {
            val expense = Expense(
                userId = userId,
                amount = amount,
                category = category,
                description = description
            )
            expenseDao.insert(expense)
        }
    }
//actualiza la vista despues de borrar
    fun deleteExpense(expense: Expense){
        viewModelScope.launch {
            expenseDao.delete(expense)
        }
    }
//mapa mutable el que se puede modificar

    fun getExpensesByCategory(userId: Long): Map<ExpenseCategory, Double>{
        val categoryTotals = mutableMapOf<ExpenseCategory, Double>()
        _expenses.value.forEach { expense ->
            categoryTotals[expense.category] =
                (categoryTotals[expense.category] ?: 0.0) + expense.amount
        }
        return categoryTotals
    }
}