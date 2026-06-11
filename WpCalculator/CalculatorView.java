import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Widok (View) w architekturze MVC.
 * Odpowiada za prezentację interfejsu użytkownika.
 * Nie zawiera żadnej logiki biznesowej.
 */
public class CalculatorView extends JFrame {

    private final JTextField screen;
    private final List<JButton> buttons = new ArrayList<>();

    public CalculatorView() {
        super("Kalkulator MVC");

        // --- Konfiguracja UI ---
        screen = new JTextField(10);
        configureScreen();

        JPanel buttonPanel = createButtonPanel();
        
        // --- Główny panel ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(screen, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // --- Konfiguracja okna ---
        setContentPane(mainPanel);
        setSize(320, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void configureScreen() {
        screen.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);
        screen.setFont(new Font("Arial", Font.PLAIN, 32));
        screen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false);
        screen.setText("0");
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(Color.BLACK);

        String[] buttonLabels = {
            "CE", "C", "<-", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            " ", "0", ".", "="
        };

        for (String label : buttonLabels) {
            if (label.equals(" ")) {
                buttonPanel.add(new JLabel());
                continue;
            }
            JButton button = new JButton(label);
            styleButton(button, label);
            buttons.add(button); // Dodaj przycisk do listy
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    private void styleButton(JButton button, String label) {
        button.setFont(new Font("Arial", Font.PLAIN, 22));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        Color darkGray = new Color(51, 51, 51);
        Color orange = new Color(255, 159, 10);
        Color lightGray = new Color(165, 165, 165);

        if ("+-*/=".contains(label)) {
            button.setBackground(orange);
            button.setForeground(Color.WHITE);
        } else if ("CE C <-".contains(label)) {
            button.setBackground(lightGray);
            button.setForeground(Color.BLACK);
        } else {
            button.setBackground(darkGray);
            button.setForeground(Color.WHITE);
        }
    }

    // Metoda do dodawania słuchacza do wszystkich przycisków
    public void addActionListenerToAllButtons(ActionListener listener) {
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
    }

    // Metoda do aktualizacji tekstu na ekranie
    public void updateScreen(String text) {
        screen.setText(text);
    }

    // Metoda do pobierania tekstu z ekranu
    public String getScreenText() {
        return screen.getText();
    }
}
