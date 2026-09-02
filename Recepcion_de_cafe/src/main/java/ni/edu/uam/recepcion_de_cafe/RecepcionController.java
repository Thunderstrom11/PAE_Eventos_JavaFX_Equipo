package ni.edu.uam.recepcion_de_cafe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RecepcionController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
