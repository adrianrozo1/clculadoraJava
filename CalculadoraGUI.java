import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;

    private double num1, num2;
    private String operation;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar ventana

        // Panel para los botones y display
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(display);

        // Inicializar botones numéricos
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            panel.add(numberButtons[i]);
        }

        // Botones de operaciones
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");

        for (int i = 0; i < 4; i++) {
            operationButtons[i].addActionListener(this);
            panel.add(operationButtons[i]);
        }

        // Botón de igual y botón de limpiar
        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        panel.add(equalsButton);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        panel.add(clearButton);

        // Añadir panel a la ventana
        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("\\d")) { // Si es un dígito
            String currentText = display.getText();
            display.setText(currentText + command);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operation = command;
            display.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error: División por cero");
                        return;
                    }
                    break;
                default:
                    break;
            }

            display.setText(String.valueOf(result));
        } else if (command.equals("C")) {
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI();
            }
        });
    }
}
