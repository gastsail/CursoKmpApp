package domain

import model.Expense
import model.ExpenseCategory

interface ExpenseRepository {
    suspend fun getAllExpenses(): List<Expense>
    suspend fun addExpense(expense: Expense)
    suspend fun editExpense(expense: Expense)
    fun getCategories(): List<ExpenseCategory>
    suspend fun deleteExpense(id: Long)
}