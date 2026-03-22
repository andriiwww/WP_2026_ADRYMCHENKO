import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField screen = new JTextField(10);
    private JButton[] buttons;
    private JButton button0 = new JButton("0");
    private JButton button1 = new JButton("1");
    private JButton button2 = new JButton("2");
    private JButton button3 = new JButton("3");
    private JButton button4 = new JButton("4");
    private JButton button5 = new JButton("5");
    private JButton button6 = new JButton("6");
    private JButton button7 = new JButton("7");
    private JButton button8 = new JButton("8");
    private JButton button9 = new JButton("9");
    private JButton buttonPlus = new JButton("+");
    private JButton buttonMinus = new JButton("-");
    private JButton buttonMultiply = new JButton("*");
    private JButton buttonDivide = new JButton("/");
    private JButton buttonEquals = new JButton("=");
    private JButton buttonClear = new JButton("C");
    private JButton buttonBackspace = new JButton("<-");
    private JPanel panel;
    private int value_1;
    private int value_2;
    String operator = "";

    public Calculator() {
        button7.addActionListener(this);

        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.add(screen, BorderLayout.NORTH);

        JPanel panelButtons = new JPanel(new GridLayout(5, 4));
        panelButtons.add(button8);

        button8.addActionListener(this);
        button9.addActionListener(this);
        button6.addActionListener(this);
        button5.addActionListener(this);
        button4.addActionListener(this);
        button3.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button0.addActionListener(this);

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("");
            }
        });

        buttonBackspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = screen.getText();
                if (!text.isEmpty()) {
                    screen.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = screen.getText();
                if (!value.isEmpty()) {
                    value_1 = Integer.parseInt(value);
                    screen.setText("");
                    operator = "+";
                }
            }

        });
        buttonDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = screen.getText();
                if (!value.isEmpty()) {
                    value_1 = Integer.parseInt(value);
                    screen.setText("");
                    operator = "/";
                }
            }
        });
        buttonEquals.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String value = screen.getText();
                if (!value.isEmpty()&& !operator.isEmpty()) {
                    value_2 = Integer.parseInt(value);
                    int result = 0;
                    switch(operator){
                        case "+":
                            result = value_1 + value_2;
                            break;
                        case "-":
                            result = value_1 - value_2;
                            break;
                        case "*":
                            result = value_1 * value_2;
                            break;
                        case "/":
                            result = value_1 / value_2;
                            break;


                    }
                    screen.setText(String.valueOf(result));
                    operator = "";

                }
            }
        });
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = screen.getText();
                if (!value.isEmpty()) {
                    value_1 = Integer.parseInt(value);
                    screen.setText("");
                    operator = "-";
                }
            }
        });

        buttonMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = screen.getText();
                if (!value.isEmpty()) {
                    value_1 = Integer.parseInt(value);
                    screen.setText("");
                    operator = "*";
                }
            }
        });

        panelMain.setBackground(Color.BLACK);
        panelButtons.setBackground(Color.BLACK);
        panelButtons.setLayout(new GridLayout(5, 4, 8, 8));


        Color darkGray = new Color(51, 51, 51);
        Color orange   = new Color(255, 159, 10);
        Color lightGray = new Color(165, 165, 165);


        JButton[] digits = {button0, button1, button2, button3, button4,
                button5, button6, button7, button8, button9};
        for (JButton b : digits) {
            b.setBackground(darkGray);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Arial", Font.PLAIN, 22));
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
        }


        JButton[] operators = {buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEquals};
        for (JButton b : operators) {
            b.setBackground(orange);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Arial", Font.PLAIN, 22));
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
        }


        JButton[] funcButtons = {buttonClear, buttonBackspace};
        for (JButton b : funcButtons) {
            b.setBackground(lightGray);
            b.setForeground(Color.BLACK);
            b.setFont(new Font("Arial", Font.PLAIN, 22));
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
        }


        screen.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);
        screen.setFont(new Font("Arial", Font.PLAIN, 32));
        screen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        screen.setHorizontalAlignment(JTextField.RIGHT);

        panelButtons.add(buttonBackspace);
        panelButtons.add(buttonClear);
        panelButtons.add(new JLabel());
        panelButtons.add(buttonDivide);
        panelButtons.add(button7);
        panelButtons.add(button8);
        panelButtons.add(button9);
        panelButtons.add(buttonMultiply);
        panelButtons.add(button4);
        panelButtons.add(button5);
        panelButtons.add(button6);
        panelButtons.add(buttonMinus);
        panelButtons.add(button1);
        panelButtons.add(button2);
        panelButtons.add(button3);
        panelButtons.add(buttonPlus);
        panelButtons.add(new JLabel());
        panelButtons.add(button0);
        panelButtons.add(new JLabel());
        panelButtons.add(buttonEquals);

        panelMain.add(panelButtons, BorderLayout.SOUTH);
        setContentPane(panelMain);

        setVisible(true);
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String digit = button.getText();
        screen.setText(screen.getText() + digit);
    }
}