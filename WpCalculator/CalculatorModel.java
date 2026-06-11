
/**
 * Model (Model) w architekturze MVC.
 * Przechowuje dane i logikę biznesową kalkulatora.
 * Nie ma żadnej wiedzy o interfejsie użytkownika.
 */
public class CalculatorModel {

    private double currentValue = 0;
    private double storedValue = 0;
    private String operator = "";
    private boolean isNewInput = true;

    // Metoda do przetwarzania wprowadzonej cyfry lub kropki
    public void handleDigit(String digit) {
        if (isNewInput) {
            if (digit.equals(".")) {
                currentValue = 0;
                isNewInput = false;
            } else {
                currentValue = Double.parseDouble(digit);
                isNewInput = false;
            }
        } else {
            // Logika zapobiegająca wielokrotnym kropkom zostanie obsłużona w kontrolerze/widoku
            // Tutaj zakładamy, że dane wejściowe są poprawne
        }
    }

    public void appendToCurrentValue(String digit) {
        // Ta metoda będzie używana przez kontroler do budowania liczby
    }


    // Metoda do ustawiania operatora
    public void setOperator(String op) {
        if (!isNewInput) {
            calculate();
        }
        storedValue = currentValue;
        operator = op;
        isNewInput = true;
    }

    // Metoda wykonująca obliczenia
    public String calculate() {
        if (operator.isEmpty() || isNewInput) {
            return getDisplayValue();
        }

        switch (operator) {
            case "+":
                currentValue = storedValue + currentValue;
                break;
            case "-":
                currentValue = storedValue - currentValue;
                break;
            case "*":
                currentValue = storedValue * currentValue;
                break;
            case "/":
                if (currentValue == 0) {
                    reset();
                    return "Błąd";
                }
                currentValue = storedValue / currentValue;
                break;
        }
        operator = "";
        isNewInput = true;
        return getDisplayValue();
    }

    // Metoda do czyszczenia ostatniego wprowadzenia
    public void clear() {
        currentValue = 0;
        isNewInput = true;
    }

    // Metoda do pełnego resetowania kalkulatora
    public void reset() {
        currentValue = 0;
        storedValue = 0;
        operator = "";
        isNewInput = true;
    }

    // Metoda do usuwania ostatniego znaku (logika uproszczona)
    public void backspace(String currentText) {
        if (currentText.length() > 1) {
            currentValue = Double.parseDouble(currentText.substring(0, currentText.length() - 1));
        } else {
            currentValue = 0;
            isNewInput = true;
        }
    }


    // Zwraca bieżącą wartość jako sformatowany ciąg znaków do wyświetlenia
    public String getDisplayValue() {
        if (currentValue == (long) currentValue) {
            return String.format("%d", (long) currentValue);
        } else {
            return String.valueOf(currentValue);
        }
    }

    // Gettery i Settery
    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public boolean isNewInput() {
        return isNewInput;
    }

    public void setNewInput(boolean newInput) {
        isNewInput = newInput;
    }
}
