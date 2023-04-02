package de.layla.jdbcgui.mainWindow.scenes;

import de.layla.jdbcgui.db.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Window;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final Dialog<String> dialog = new Dialog<>();

    @FXML
    private GridPane root;
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
        this.dialog.setHeaderText("Warning");
        this.dialog.setContentText("User not found");
        dialog.setResizable(false);
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> window.hide());
    }

    @FXML
    private void login() {
        Connection connection = databaseConnection.connect(usernameField.getText(), passwordField.getText());
        if (connection == null) {
            dialog.showAndWait();
        }
    }

    @FXML
    private void signup() {
        System.out.println("signup");
    }

}
