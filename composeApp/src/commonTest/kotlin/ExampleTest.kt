import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleTest {

    @Test
    fun sum_must_succedd() {
        // Given
        val x = 5
        val y = 10

        // When
        val result = x+y

        // Then
        assertEquals(15, result)
    }

    @Test
    fun sum_must_fail() {
        // Given
        val x = 5
        val y = 10

        // When
        val result = x+y

        // Then
        assertNotEquals(10, result)
    }

    @Test
    fun expense_model_list_test() {
        // Given
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(id = 1, amount = 4.5, category = ExpenseCategory.CAR, description = "Combustible")

        // When
        expenseList.add(expense)

        // Then
        assertContains(expenseList, expense)
    }

    @Test
    fun expense_model_param_test_success() {
        // Given
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(id = 1, amount = 4.5, category = ExpenseCategory.OTHER, description = "Combustible")
        val expense2 =  Expense(id = 2, amount = 14.5, category = ExpenseCategory.OTHER, description = "Limpieza")

        // When
        expenseList.add(expense)
        expenseList.add(expense2)

        // Then
        assertEquals(expenseList[0].category, expenseList[1].category)
    }

    @Test
    fun expense_model_param_test_fail() {
        // Given
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(id = 1, amount = 4.5, category = ExpenseCategory.CAR, description = "Combustible")
        val expense2 =  Expense(id = 2, amount = 14.5, category = ExpenseCategory.OTHER, description = "Limpieza")

        // When
        expenseList.add(expense)
        expenseList.add(expense2)

        // Then
        assertNotEquals(expenseList[0].category, expenseList[1].category)
    }
}