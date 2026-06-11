import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Główna klasa kalkulatora, dziedzicząca po JFrame i implementująca ActionListener
public class Calculator extends JFrame implements ActionListener {

    // --- Pola klasy ---
    private final JTextField screen;    // Ekran do wyświetlania liczb i wyników
    private double firstValue;          // Przechowuje pierwszą wartość w operacji
    private String operator;            // Przechowuje wybrany operator (+, -, *, /)
    private boolean isNewInput;         // Flaga wskazująca, czy następne wpisanie cyfry powinno rozpocząć nową liczbę

    // --- Konstruktor ---
    public Calculator() {
        super("Prosty kalkulator"); // Ustawienie tytułu okna

        // Inicjalizacja zmiennych stanu
        screen = new JTextField(10);
        isNewInput = true;
        operator = "";
        firstValue = 0;

        // --- Konfiguracja interfejsu użytkownika (UI) ---
        Color darkGray = new Color(51, 51, 51);      // Kolor dla przycisków z cyframi
        Color orange = new Color(255, 159, 10);      // Kolor dla przycisków operatorów
        Color lightGray = new Color(165, 165, 165);  // Kolor dla przycisków funkcyjnych

        // Konfiguracja ekranu
        screen.setBackground(Color.BLACK);
        screen.setForeground(Color.WHITE);
        screen.setFont(new Font("Arial", Font.PLAIN, 32));
        screen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false); // Użytkownik nie może pisać bezpośrednio na ekranie
        screen.setText("0");

        // Panel na przyciski z siatką (GridLayout)
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(Color.BLACK);

        // Etykiety dla wszystkich przycisków w odpowiedniej kolejności
        String[] buttonLabels = {
                "CE", "C", "<-", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                " ", "0", ".", "="
        };

        // Pętla tworząca i konfigurująca przyciski
        for (String label : buttonLabels) {
            if (label.equals(" ")) {
                buttonPanel.add(new JLabel()); // Dodanie pustej etykiety jako wypełniacza
                continue;
            }
            JButton button = new JButton(label);
            button.addActionListener(this); // Każdy przycisk będzie obsługiwany przez tę samą metodę actionPerformed
            button.setFont(new Font("Arial", Font.PLAIN, 22));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFocusPainted(false);

            // Ustawienie kolorów w zależności od typu przycisku
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
            buttonPanel.add(button);
        }

        // --- Główny panel aplikacji ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(screen, BorderLayout.NORTH); // Ekran na górze
        mainPanel.add(buttonPanel, BorderLayout.CENTER); // Przyciski w centrum

        // --- Konfiguracja okna ---
        setContentPane(mainPanel);
        setSize(320, 500);
        setResizable(false); // Okno nie może zmieniać rozmiaru
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zamknięcie aplikacji po zamknięciu okna
        setLocationRelativeTo(null); // Wyśrodkowanie okna na ekranie
        setVisible(true); // Uczynienie okna widocznym
    }

    // --- Główna metoda obsługi zdarzeń (kliknięć przycisków) ---
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Pobranie etykiety klikniętego przycisku

        if ("0123456789.".contains(command)) {
            handleNumberInput(command); // Obsługa wprowadzania cyfr i kropki
        } else if ("+-*/".contains(command)) {
            handleOperatorInput(command); // Obsługa operatorów
        } else if ("=".equals(command)) {
            handleEquals(); // Obsługa przycisku równości
        } else if ("C".equals(command)) {
            // "C" - czyści ostatnio wprowadzoną liczbę
            screen.setText("0");
            isNewInput = true;
        } else if ("CE".equals(command)) {
            // "CE" - czyści całe działanie (resetuje kalkulator)
            screen.setText("0");
            isNewInput = true;
            firstValue = 0;
            operator = "";
        } else if ("<-".equals(command)) {
            handleBackspace(); // Obsługa przycisku cofania
        }
    }

    // --- Metody pomocnicze ---

    // Obsługuje wprowadzanie cyfr
    private void handleNumberInput(String number) {
        if (isNewInput) {
            screen.setText(number);
            isNewInput = false;
        } else {
            if (screen.getText().equals("0") && !number.equals(".")) {
                screen.setText(number);
            } else if (number.equals(".") && screen.getText().contains(".")) {
                // Nie pozwala na dodanie drugiej kropki
                return;
            } else {
                screen.setText(screen.getText() + number);
            }
        }
    }

    // Obsługuje wybór operatora
    private void handleOperatorInput(String op) {
        if (!isNewInput) {
            calculate(); // Wykonuje poprzednie działanie, jeśli istnieje
        }
        firstValue = Double.parseDouble(screen.getText());
        operator = op;
        isNewInput = true;
    }

    // Obsługuje naciśnięcie przycisku "="
    private void handleEquals() {
        if (!operator.isEmpty()) {
            calculate();
            operator = ""; // Resetuje operator po naciśnięciu "="
        }
        isNewInput = true;
    }

    // Obsługuje usuwanie ostatniego znaku
    private void handleBackspace() {
        String currentText = screen.getText();
        if (currentText.length() > 1) {
            screen.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            screen.setText("0");
            isNewInput = true;
        }
    }

    // Wykonuje obliczenia
    private void calculate() {
        if (isNewInput) return; // Nie wykonuje obliczeń, jeśli nie wprowadzono drugiej liczby

        double secondValue = Double.parseDouble(screen.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue == 0) {
                    screen.setText("Błąd"); // Obsługa dzielenia przez zero
                    operator = "";
                    isNewInput = true;
                    return;
                }
                result = firstValue / secondValue;
                break;
        }

        // Formatowanie wyniku: jeśli jest to liczba całkowita, nie pokazuj części dziesiętnej
        if (result == (long) result) {
            screen.setText(String.format("%d", (long) result));
        } else {
            screen.setText(String.format("%s", result));
        }
        firstValue = result; // Zapisuje wynik jako pierwszą wartość dla kolejnych operacji
    }

    // --- Metoda główna ---
    public static void main(String[] args) {
        // Uruchamia aplikację w wątku dystrybucji zdarzeń Swing (bezpieczny dla GUI)
        SwingUtilities.invokeLater(Calculator::new);
    }
}
