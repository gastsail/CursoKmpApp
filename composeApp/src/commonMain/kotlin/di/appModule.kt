package di

import com.expenseApp.db.AppDatabase
import data.ExpenseRepoImpl
import domain.ExpenseRepository
import org.koin.dsl.module
import presentacion.ExpensesViewModel

fun appModule(appDatabase: AppDatabase) = module {
    single<ExpenseRepository> { ExpenseRepoImpl(appDatabase) }
    factory { ExpensesViewModel(get()) }
}
