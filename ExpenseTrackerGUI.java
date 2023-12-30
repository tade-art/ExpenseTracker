import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExpenseTrackerGUI{
    private final ExpenseTracker expenseTracker;
    private JTextArea expenseTextArea;
    public ExpenseTrackerGUI(ExpenseTracker expenseTracker){
        this.expenseTracker = expenseTracker;
        SwingUtilities.invokeLater(this::ETDriver);
    }

    private void ETDriver(){
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField descriptionField = new JTextField(20);
        JTextField amountField = new JTextField(10);

        JButton addButton = new JButton("Add Expense");
        JButton totalButton = new JButton("Calculate Total");
        JButton showExpensesButton = new JButton("Show Expenses");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(addButton);
        panel.add(totalButton);
        panel.add(showExpensesButton);

        addButton.setBackground(new Color(60, 141, 188));
        addButton.setForeground(Color.WHITE);
        totalButton.setBackground(new Color(60, 141, 188));
        totalButton.setForeground(Color.WHITE);
        showExpensesButton.setBackground(new Color(60, 141, 188));
        showExpensesButton.setForeground(Color.WHITE);

        expenseTextArea = new JTextArea(10, 30);
        expenseTextArea.setEnabled(false);
        expenseTextArea.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(expenseTextArea);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Expense expense = new Expense(description, amount);
            expenseTracker.addExpense(expense);

            JOptionPane.showMessageDialog(null, "Expense added successfully!");
        });

        totalButton.addActionListener(e -> {
            double total = expenseTracker.calculateTotal();
            JOptionPane.showMessageDialog(null, "Total Expense: £" + total);
        });

        showExpensesButton.addActionListener(e -> displayExpenses());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void displayExpenses(){
        List<Expense> expenses = expenseTracker.getExpenses();
        if (expenses.isEmpty()){
            expenseTextArea.setText("No expenses recorded.");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder("User's Expenses:\n");
        for (Expense expense : expenses){
            stringBuilder.append("Description: ").append(expense.description())
                    .append(", Amount: £").append(expense.amount()).append("\n");
        }
        expenseTextArea.setText(stringBuilder.toString());
    }

    public static void main(String[] args){
        ExpenseTracker expenseTracker = new ExpenseTrackerImpl();
        new ExpenseTrackerGUI(expenseTracker);
    }
}
