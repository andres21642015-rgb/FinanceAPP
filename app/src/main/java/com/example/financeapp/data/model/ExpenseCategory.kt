package com.example.financeapp.data.model
/**
 * Expense category numeración de categorias de gastos
 **/


enum class ExpenseCategory (val displayName: String){
    FOOD("Alimentación"),
    TRANSPORT("Transporte"),
    ENTERTAINMENT("Entretenimiento"),
    BILLS("Servicios"),
    SHOPPING("Compras"),
    HEALTH("Salud"),
    EDUCATION("Educación"),
    TRAVEL("Viajes"),
    OTHER("Otros")

}