import javax.swing.*;
import java.awt.*;

public class ExpenseTrackerGUI{
    private ExpenseTracker expenseTracker;

    public ExpenseTrackerGUI(ExpenseTracker expenseTracker){
        this.expenseTracker = expenseTracker;
        SwingUtilities.invokeLater( ()-> ETDriver());
    }

    private void ETDriver(){
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        JTextField descriptionField = new JTextField(20);
        JTextField amountField = new JTextField(10);

        JButton addButton = new JButton("Add Expense");
        JButton totalButton = new JButton("Calculate Total");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(addButton);
        panel.add(totalButton);

        addButton.setBackground(new Color(60, 141, 188));
        addButton.setForeground(Color.WHITE);
        totalButton.setBackground(new Color(60, 141, 188));
        totalButton.setForeground(Color.WHITE);

        addButton.addActionListener(e -> {
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Expense expense = new Expense(description, amount);
            expenseTracker.addExpense(expense);

            JOptionPane.showMessageDialog(null, "Expense added successfully!");
        });

        totalButton.addActionListener(e -> {
            double total = expenseTracker.calculateTotal();
            JOptionPane.showMessageDialog(null, "Total Expense: $" + total);
        });

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        ExpenseTracker expenseTracker = new ExpenseTrackerImpl();
        new ExpenseTrackerGUI(expenseTracker);
    }
}
