/**The View class is explained in detail in the documentation, in chapter 4*/

package polynomialcalculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class View extends JFrame {

    private JLabel p1Label;
    private JTextField p1TextField;
    private JLabel p2Label;
    private JTextField p2TextField;
    private JComboBox operationComboBox;
    private JButton calculateButton;
    private JLabel resultLabel;

    public View() {
        JPanel panel = new JPanel();
        p1Label = new JLabel("Polynomial 1:");
        p1TextField = new JTextField(30);
        p2Label = new JLabel("Polynomial 2:");
        p2TextField = new JTextField(30);
        String[] s1 = {"Addition", "Subtraction", "Multiplication", "Division", "Derivative", "Integration"};
        operationComboBox = new JComboBox(s1);
        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel("Result:");

        panel.setLayout(new GridLayout(0,1));
        panel.add(p1Label);
        panel.add(p1TextField);
        panel.add(p2Label);
        panel.add(p2TextField);
        panel.add(operationComboBox);
        panel.add(calculateButton);
        panel.add(resultLabel);
        this.setContentPane(panel);
        this.setSize(600, 200);
        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public String getPolynomial1() {
        return p1TextField.getText();
    }

    public String getPolynomial2() {
        return p2TextField.getText();
    }

    public String getOperation() {
        try {
            return Objects.requireNonNull(operationComboBox.getSelectedItem()).toString();
        } catch(NullPointerException e) {
            return null;
        }
    }

    public void setResult(String result) {
        resultLabel.setText(result);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

}
