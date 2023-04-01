package de.layla.jdbcgui.mainWindow.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Signup extends GridPane {

    public Signup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignupView.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
