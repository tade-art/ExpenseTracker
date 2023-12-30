import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerImpl implements ExpenseTracker{
    private List<Expense> expenses;

    public ExpenseTrackerImpl(){
        expenses = new ArrayList<>();
    }

    @Override
    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    @Override
    public double calculateTotal(){
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}
