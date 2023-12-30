import java.util.List;

public interface ExpenseTracker{
    void addExpense(Expense expense);
    double calculateTotal();
    List<Expense> getExpenses();
}
