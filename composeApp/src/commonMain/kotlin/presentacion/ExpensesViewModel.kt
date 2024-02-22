package presentacion

import domain.ExpenseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.Expense
import model.ExpenseCategory
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

sealed class ExpensesUiState {
    object Loading : ExpensesUiState()
    data class Success(val expenses: List<Expense>, val total: Double) : ExpensesUiState()
    data class Error(val message: String) : ExpensesUiState()
}

class ExpensesViewModel(private val repo: ExpenseRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ExpensesUiState>(ExpensesUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private var allExpenses: MutableList<Expense> = mutableListOf()

    init {
        getAllExpenses()
    }

    private fun updateExpenseList() {
        viewModelScope.launch {
            try {
                allExpenses = repo.getAllExpenses().toMutableList()
                //delay(5000) Only used to see circular progress in iOS since its a local API and its fast.
                updateState(ExpensesUiState.Success(allExpenses, allExpenses.sumOf { it.amount }))
            } catch (e: Exception) {
                updateState(ExpensesUiState.Error(e.message ?: "Unknown error occurred"))
            }
        }
    }

    private fun getAllExpenses() {
        updateExpenseList()
    }

    fun addExpense(expense: Expense) {
        repo.addExpense(expense)
        updateExpenseList()
    }

    fun editExpense(expense: Expense) {
        repo.editExpense(expense)
        updateExpenseList()
    }

    fun deleteExpense(expense: Expense) {
        repo.deleteExpense(expense)
        updateExpenseList()
    }

    private fun updateState(state: ExpensesUiState) {
        _uiState.value = state
    }

    fun getExpenseWithID(id: Long): Expense {
        return allExpenses.first { it.id == id }
    }

    fun getCategories(): List<ExpenseCategory> {
        return repo.getCategories()
    }
}