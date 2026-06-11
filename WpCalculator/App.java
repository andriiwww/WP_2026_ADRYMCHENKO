import javax.swing.SwingUtilities;

/**
 * Główna klasa aplikacji.
 * Tworzy i łączy Model, Widok i Kontroler, a następnie uruchamia aplikację.
 */
public class App {
    public static void main(String[] args) {
        // Uruchamia aplikację w wątku dystrybucji zdarzeń Swing
        SwingUtilities.invokeLater(() -> {
            // Tworzy instancje Modelu, Widoku i Kontrolera
            CalculatorModel model = new CalculatorModel();
            CalculatorView view = new CalculatorView();
            new CalculatorController(model, view); // Kontroler łączy model i widok

            // Ustawia widok jako widoczny
            view.setVisible(true);
        });
    }
}
