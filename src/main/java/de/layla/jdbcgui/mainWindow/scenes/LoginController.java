package de.layla.jdbcgui.mainWindow.scenes;

import de.layla.jdbcgui.db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void login() {

    }

    @FXML
    private void signup() {
        if (!databaseConnection.createNewUser(usernameField.getText(), passwordField.getText())) {
            showDialog("User creation failed.");
        } else {
            showDialog("User successfully created.");
        }
    }

    private void showDialog(String text) {
        Dialog<String> dialog = new Dialog<>();
        Window window = dialog.getDialogPane().getScene().getWindow();
        dialog.setContentText(text);
        window.setOnCloseRequest(e -> window.hide());
        dialog.showAndWait();
    }

}
