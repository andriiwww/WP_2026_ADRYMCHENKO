import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Kontroler (Controller) w architekturze MVC.
 * Łączy Widok z Modelem. Nasłuchuje na akcje użytkownika,
 * wywołuje logikę w modelu i aktualizuje widok.
 */
public class CalculatorController implements ActionListener {

    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        // Rejestruje siebie jako słuchacza dla wszystkich przycisków w widoku
        this.view.addActionListenerToAllButtons(this);
        updateView(); // Ustawia początkowy stan widoku
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Sprawdza, czy wciśnięto cyfrę lub kropkę
        if ("0123456789.".contains(command)) {
            handleDigitInput(command);
        }
        // Sprawdza, czy wciśnięto operator
        else if ("+-*/".contains(command)) {
            model.setOperator(command);
        }
        // Sprawdza, czy wciśnięto "="
        else if ("=".equals(command)) {
            String result = model.calculate();
            view.updateScreen(result);
        }
        // Sprawdza, czy wciśnięto "C"
        else if ("C".equals(command)) {
            model.clear();
        }
        // Sprawdza, czy wciśnięto "CE"
        else if ("CE".equals(command)) {
            model.reset();
        }
        // Sprawdza, czy wciśnięto "<-"
        else if ("<-".equals(command)) {
            model.backspace(view.getScreenText());
        }

        // Po każdej akcji aktualizuje widok
        updateView();
    }

    // Prywatna metoda do obsługi wprowadzania cyfr
    private void handleDigitInput(String digit) {
        String currentText = view.getScreenText();

        // Jeśli jest to nowy wpis, ekran jest resetowany
        if (model.isNewInput()) {
            // Zapobiega wpisaniu kropki jako pierwszego znaku
            if (digit.equals(".")) {
                view.updateScreen("0.");
            } else {
                view.updateScreen(digit);
            }
            model.setNewInput(false);
        } else {
            // Zapobiega dodaniu drugiej kropki
            if (digit.equals(".") && currentText.contains(".")) {
                return;
            }
            view.updateScreen(currentText + digit);
        }
        // Aktualizuje wartość w modelu
        model.setCurrentValue(Double.parseDouble(view.getScreenText()));
    }


    // Metoda do aktualizacji ekranu w widoku na podstawie danych z modelu
    private void updateView() {
        view.updateScreen(model.getDisplayValue());
    }
}
